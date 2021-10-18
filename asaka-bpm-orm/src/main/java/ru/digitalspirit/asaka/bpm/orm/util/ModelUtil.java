package ru.digitalspirit.asaka.bpm.orm.util;

import ru.digitalspirit.asaka.bpm.orm.configuration.Model;
import ru.digitalspirit.asaka.bpm.orm.configuration.ModelConfiguration;
import ru.digitalspirit.asaka.bpm.orm.exception.BpmOrmException;

import java.io.Serializable;
import java.lang.reflect.Field;

public class ModelUtil {

    private ModelUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Serializable getId(Object entity) {
        Field field = getAppropriateField(entity.getClass(), "id");
        if (field == null) {
            field = getAppropriateField(entity.getClass(), "applicationID");
        }
        if (field == null) {
            throw new BpmOrmException("Unsupported entity!");
        }
        return (Serializable) getValue(entity, field);
    }

    public static boolean isModel(Class<? extends Object> clazz) {
        for (String modelPackage : ModelConfiguration.MODEL_PACKAGE.values()) {
            if (clazz.getName().startsWith(modelPackage) && hasIdField(clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmbeddedModel(Class<? extends Object> clazz) {
        for (String modelPackage : ModelConfiguration.MODEL_PACKAGE.values()) {
            if (clazz.getName().startsWith(modelPackage) && !hasIdField(clazz)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasIdField(Class<? extends Object> clazz) {
        return getAppropriateField(clazz, "id") != null || getAppropriateField(clazz, "applicationID") != null;
    }

    public static Object getValue(Object object, String fieldName) {
        try {
            Field field = null;
            try {
                field = object.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                field = getAppropriateField(object.getClass(), fieldName);
            }
            return field != null ? getValue(object, field) : null;
        } catch (Exception e) {
            throw new BpmOrmException(e.getMessage());
        }
    }

    public static Object getValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new BpmOrmException(e.getMessage());
        }
    }

    public static String getPackage(Model model) {
        return ModelConfiguration.MODEL_PACKAGE.get(model);
    }

    private static Field getAppropriateField(Class<? extends Object> clazz, String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }


}
