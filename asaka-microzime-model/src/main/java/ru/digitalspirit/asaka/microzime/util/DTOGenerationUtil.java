package ru.digitalspirit.asaka.microzime.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaDocSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DTOGenerationUtil {

    private static String COMMON_XSD_KEY = "COMMON";

    private static String MAIN_DOC_KEY = "CLASS_DOCUMENTATION";

    private static String DTO_SUFFIX = "DTO";

    public static void generateDTO(String javaSrcDir, String xsdDir, String modelPackage, String dtoPackage, String model) throws FileNotFoundException, UnsupportedEncodingException {
        Map<String, List<File>> xsdMap = getXSDClasses(xsdDir);
        List<File> srcFiles = getJavaFiles(javaSrcDir, modelPackage);
        for (File srcFile : srcFiles) {
            JavaClassSource modelSource;
            try {
                modelSource = Roaster.parse(JavaClassSource.class, srcFile);
            } catch (ParserException ex) {
                continue;
            }
            JavaClassSource dtoSource = generateDtoSource(modelSource, dtoPackage, xsdMap, model);
            if (dtoSource != null) {
                saveDTOSource(dtoSource, javaSrcDir);
            }
        }
    }

    private static JavaClassSource generateDtoSource(JavaClassSource modelSource, String dtoPackage, Map<String, List<File>> xsdMap, String model) throws UnsupportedEncodingException {
        String className = modelSource.getName();
        File xsd = findXsdForClass(className, xsdMap);
        if (xsd == null) {
            return null;
        }
        Map<String, XSDField> docMap = getDocumentationMap(className, xsd);
        JavaClassSource dtoJava = Roaster.create(JavaClassSource.class);
        dtoJava.setPackage(dtoPackage).setName(className + DTO_SUFFIX);
        if (docMap != null && docMap.get(MAIN_DOC_KEY) != null) {
            dtoJava.addAnnotation(ApiModel.class).setLiteralValue("value", getDescriptionForFieldWithModel(MAIN_DOC_KEY, docMap, model));
            dtoJava.addAnnotation(ToString.class);
        }
        addClassProperties(modelSource, dtoJava, docMap);
        JavaDocSource javaDoc = dtoJava.getJavaDoc();
        javaDoc.setFullText("This DTO class was generated automatically");
        return dtoJava;
    }

    private static void addClassProperties(JavaClassSource modelSource, JavaClassSource dtoSource, Map<String, XSDField> docMap) throws UnsupportedEncodingException {
        for (MethodSource property : modelSource.getMethods()) {
            if (!property.getName().startsWith("get")) {
                continue;
            }
            String fieldName = property.getName().substring(3);
            Type type = property.getReturnType();
            String newTypeName = processDate(type);
            if (newTypeName == null) {
                newTypeName = processModel(type, modelSource.getPackage(), dtoSource.getPackage());
            }
            if (newTypeName == null) {
                newTypeName = type.getQualifiedNameWithGenerics();
            }
            dtoSource.addProperty(newTypeName, fieldName);
            if (docMap != null && docMap.get(fieldName.toLowerCase()) != null && dtoSource.getMethod(getGetter(fieldName)) != null) {
                addAnnotationToGetter(dtoSource, fieldName, JsonProperty.class, getXsdFieldName(fieldName.toLowerCase(), docMap));
                addAnnotationToGetter(dtoSource, fieldName, ApiModelProperty.class, getDescriptionForField(fieldName.toLowerCase(), docMap));
            }
        }
    }

    private static void addAnnotationToGetter(JavaClassSource src, String fieldName, Class annotation, String value) {
        src.getMethod("get" + fieldName).addAnnotation(annotation).setLiteralValue("value", value);
    }

    private static String getGetter(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static String getDescriptionForFieldWithModel(String fieldName, Map<String, XSDField> docMap, String model) {
        return "\"" + docMap.get(fieldName).getDocumentation().replace("\n", "\\n")  + " (" + model + ")" + "\"";
    }

    private static String getDescriptionForField(String fieldName, Map<String, XSDField> docMap) {
        return "\"" + docMap.get(fieldName).getDocumentation().replace("\n", "\\n") + "\"";
    }

    private static String getXsdFieldName(String fieldName, Map<String, XSDField> docMap) {
        return "\"" + docMap.get(fieldName).getName() + "\"";
    }

    private static String processModel(Type type, String modelPackage, String dtoPackage) {
        if (!type.isParameterized() && type.getQualifiedName().startsWith(modelPackage + ".")) {
            return (dtoPackage + "." + type.getSimpleName() + DTO_SUFFIX);
        } else if (type.isParameterized()) {
            String qName = type.getQualifiedNameWithGenerics();
            StringBuilder newType = new StringBuilder();
            for (Object gType : type.getTypeArguments()) {
                String cType = ((Type)gType).getQualifiedName();
                if (!((Type)gType).isParameterized() && ((Type)gType).getQualifiedName().startsWith(modelPackage + ".")) {
                    cType = dtoPackage + "." + ((Type)gType).getSimpleName() + DTO_SUFFIX;
                }
                if (newType.length() != 0) {
                    newType.append(", ");
                }
                newType.append(cType);
            }
            return qName.substring(0, qName.indexOf("<") + 1) + newType.toString() + ">";
        }
        return null;
    }

    private static String processDate(Type type) {
        if (!type.isParameterized()) {
            if ("java.util.Date".equals(type.getQualifiedName()) ||
                    "java.sql.Date".equals(type.getQualifiedName()) ||
                    "java.sql.Time".equals(type.getQualifiedName()) ||
                    "java.sql.Timestamp".equals(type.getQualifiedName())) {
                return "java.lang.String";
            }
        }
        return null;
    }


    private static void saveDTOSource(JavaClassSource dto, String srcDir) {
        File file = new File(srcDir);
        String[] path = dto.getPackage().split("\\.");
        for (String folder : path) {
            file = new File(file, folder);
        }
        file = new File(file, dto.getName() + ".java");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
           /* OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            byte[] bytes = dto.toString().getBytes();
            char[] chars = new char[bytes.length];
            for (int i = 0; i < bytes.length; i++)
                chars[i] = (char)bytes[i];
            writer.write(chars);
            writer.close();*/
            //String s = new String(dto.toString().getBytes(StandardCharsets.UTF_8), "UTF-8");
            Files.write(Paths.get(file.getAbsolutePath()), dto.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Map<String, XSDField> getDocumentationMap(String className, File file) {
        Document xsd = getDocument(file);
        Element element = getXSDComplexType(className, xsd);
        if (element == null) {
            throw new RuntimeException("Not find class " + className + "!");
        }
        Map<String, XSDField> docMap = new HashMap<>();
        docMap.put(MAIN_DOC_KEY, getDocumentation(element));

        NodeList fields = element.getElementsByTagNameNS("*", "element");
        for (int i = 0; i < fields.getLength(); i++) {
            Element field = (Element)fields.item(i);
            docMap.put(field.getAttribute("name").toLowerCase(), getDocumentation(field));
        }
        return docMap;
    }

    private static XSDField getDocumentation(Element node) {
        NodeList annotations = node.getElementsByTagNameNS("*", "annotation");
        for (int i = 0; i < annotations.getLength(); i++) {
            Element annotation = (Element)annotations.item(i);
            if (annotation.getParentNode().equals(node)) {
                NodeList documentations = node.getElementsByTagNameNS("*", "documentation");
                return new XSDField(node.getAttribute("name"), documentations.item(0).getTextContent());
            }
        }
        return new XSDField(node.getAttribute("name"), "");
    }

    private static File findXsdForClass(String className, Map<String, List<File>> fileMap) {
        if (fileMap.containsKey(className.toLowerCase())) {
            for (File file : fileMap.get(className.toLowerCase())) {
                if (isContainClass(className, file)) {
                    return file;
                }
            }
        }

        else if (className.toLowerCase().endsWith("type") && fileMap.containsKey(className.toLowerCase().substring(0, className.toLowerCase().lastIndexOf("type")))) {
            String name = className.toLowerCase().substring(0, className.toLowerCase().lastIndexOf("type"));
            for (File file : fileMap.get(name)) {
                if (isContainClass(className, file)) {
                    return file;
                }
            }
        }

        else {
            for (Map.Entry<String, List<File>> entry: fileMap.entrySet()){
                for (File file : entry.getValue()) {
                    if (isContainClass(className, file)) {
                        return file;
                    }
                }
            }

        }

        for (File file : fileMap.get("COMMON")) {
            if (isContainClass(className, file)) {
                return file;
            }
        }
        return null;
        //throw new RuntimeException("Not find class " + className + "!");
    }

    private static boolean isContainClass(String className, File file) {
        Document xsd = getDocument(file);
        return getXSDComplexType(className, xsd) != null;
    }

    private static Element getXSDComplexType(String className, Document xsd) {
        NodeList complexTypes = xsd.getElementsByTagNameNS("*", "complexType");
        for (int i = 0; i < complexTypes.getLength(); i++) {
            Element complexType = (Element)complexTypes.item(i);
            if (complexType.getAttribute("name").equalsIgnoreCase(className)) {
                return complexType;
            }
        }
        return null;
    }

    private static Document getDocument(File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(file);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Map<String, List<File>> getXSDClasses(String xsdDir) {
        List<File> files = new ArrayList<>();
        addXsdFileRecursively(new File(xsdDir), files);
        Map<String, List<File>> xsdClasses = new HashMap<>();
        for (File file : files) {
            System.out.println("11111111111111111  file " + file.getName());
            if (file.getName().toLowerCase().contains("common")) {
                System.out.println("11111111111111111");
                addFile(xsdClasses, "COMMON", file);
            }
            addFile(xsdClasses, getXSDName(file.getName()).toLowerCase(), file);
        }
        return xsdClasses;
    }

    private static void addFile(Map<String, List<File>> map, String key, File file) {
        if (map.containsKey(key)) {
            List<File> files = map.get(key);
            if (files == null) {
                files = new ArrayList<>();
            }
            files.add(file);
        } else {
            List<File> files = new ArrayList<>();
            files.add(file);
            map.put(key, files);
        }
    }

    private static String getXSDName(String xsdName) {
        return xsdName.substring(0, xsdName.length() - 4);
    }

    private static void addXsdFileRecursively(File file, List<File> files) {
        if (file.isFile() && file.getName().endsWith(".xsd")) {
            files.add(file);
        } else if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                addXsdFileRecursively(f, files);
            }
        }
    }

    private static List<File> getJavaFiles(String srcDir, String packageName) {
        String dirPath = srcDir + "/" + packageName.replaceAll("\\.", "/");
        File packageDir = new File(dirPath);
        if (packageDir.isDirectory()) {
            return Arrays.asList(packageDir.listFiles());
        }
        return null;
    }

// FOR ANT TASK

    private String javaSrcDir;

    private String xsdDir;

    private String modelPackage;

    private String dtoPackage;

    private String model;

    public String getJavaSrcDir() {
        return javaSrcDir;
    }

    public void setJavaSrcDir(String javaSrcDir) {
        this.javaSrcDir = javaSrcDir;
    }

    public String getXsdDir() {
        return xsdDir;
    }

    public void setXsdDir(String xsdDir) {
        this.xsdDir = xsdDir;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public void setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void execute() throws FileNotFoundException, UnsupportedEncodingException {
        generateDTO(javaSrcDir, xsdDir, modelPackage, dtoPackage, model);
    }
}
