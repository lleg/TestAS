package ru.digitalspirit.asaka.microloan.util;

public class XSDField {

    private String name;

    private String documentation;

    public XSDField(String name, String documentation) {
        this.name = name;
        this.documentation = documentation;
    }

    public XSDField() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
