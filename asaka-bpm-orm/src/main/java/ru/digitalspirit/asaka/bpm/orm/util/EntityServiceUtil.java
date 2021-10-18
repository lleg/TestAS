package ru.digitalspirit.asaka.bpm.orm.util;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import ru.digitalspirit.asaka.bpm.orm.exception.BpmOrmException;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public class EntityServiceUtil {

    private EntityServiceUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Object prepareEntityForSave(Object entity, Session session, List<String> nullPaths, String path) {
        Serializable id = ModelUtil.getId(entity);

        Object persistedEntity = null;
        if (id != null) {
            persistedEntity = session.get(entity.getClass(), id);
        }
        persistedEntity = initializeHibernateObject(persistedEntity);

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (ModelUtil.isModel(field.getType())) {
                prepareModelFieldForSave(field, entity, persistedEntity, session, nullPaths, path);
            } else if (Collection.class.isAssignableFrom(field.getType()) && ModelUtil.isModel(getGenericTypeOfCollection(field))) {
                prepareCollectionFieldForSave(field, entity, persistedEntity, session, nullPaths, path);
            } else if (ModelUtil.isEmbeddedModel(field.getType())) {
                prepareEmbeddedModelFieldForSave(field, entity, persistedEntity, session, nullPaths, path);
            }
        }
        return entity;
    }

    public static Object getObjectWithPresetFields(Object entity, List<String> presetPaths, String path) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (ModelUtil.isModel(field.getType())) {
                getModelFieldWithPresetFields(field, entity, presetPaths, path);
            } else if (Collection.class.isAssignableFrom(field.getType()) && ModelUtil.isModel(getGenericTypeOfCollection(field))) {
                getCollectionFieldWithPresetFields(field, entity, presetPaths, path);
            }
        }
        return entity;
    }

    public static Object initializeHibernateObject(Object object) {
        Hibernate.initialize(object);
        if (object instanceof HibernateProxy) {
            object = ((HibernateProxy) object).getHibernateLazyInitializer().getImplementation();
        }
        return object;
    }

    private static void prepareModelFieldForSave(Field field, Object entity, Object persistedEntity, Session session, List<String> nullPaths, String path) {
        Object value = ModelUtil.getValue(entity, field);
        if (value == null) {
            if (!matchFields(nullPaths, path, field.getName()) && persistedEntity != null) {
                setValue(entity, field, ModelUtil.getValue(persistedEntity, field));
            }
        } else {
            setValue(entity, field, prepareEntityForSave(ModelUtil.getValue(entity, field), session, nullPaths, getNewPath(path, field.getName())));
        }
    }

    private static void prepareCollectionFieldForSave(Field field, Object entity, Object persistedEntity, Session session, List<String> nullPaths, String path) {
        Object value = ModelUtil.getValue(entity, field);
        if ((value == null || ((Collection)value).isEmpty())) {
            if (!matchFields(nullPaths, path, field.getName()) && persistedEntity != null) {
                setValue(entity, field, ModelUtil.getValue(persistedEntity, field));
            }
            return;
        }
        for (Object val : (Collection)value) {
            val = prepareEntityForSave(val, session, nullPaths, getNewPath(path, field.getName()));
        }
        if (!matchFields(nullPaths, path, field.getName()) && persistedEntity != null) {
            addPersistedEntitiesToCollection((Collection) value, (Collection) ModelUtil.getValue(persistedEntity, field));
        }
    }

    private static void prepareEmbeddedModelFieldForSave(Field field, Object entity, Object persistedEntity, Session session, List<String> nullPaths, String path) {
        Object value = ModelUtil.getValue(entity, field);
        if (value == null) {
            if (!matchFields(nullPaths, path, field.getName()) && persistedEntity != null) {
                setValue(entity, field, ModelUtil.getValue(persistedEntity, field));
            }
        } else {
            setValue(entity, field, value);
        }
    }

    private static void getModelFieldWithPresetFields(Field field, Object entity, List<String> presetPaths, String path) {
        Object value = ModelUtil.getValue(entity, field);
        if (!matchFieldsWithRelated(presetPaths, path, field.getName())) {
            setValue(entity, field, null);
        } else if (value != null) {
            getObjectWithPresetFields(value, presetPaths, getNewPath(path, field.getName()));
        }
    }

    private static void getCollectionFieldWithPresetFields(Field field, Object entity, List<String> presetPaths, String path) {
        Object value = ModelUtil.getValue(entity, field);
        if (!matchFieldsWithRelated(presetPaths, path, field.getName())) {
            setValue(entity, field, null);
        } else  if (value != null) {
            for (Object val : (Collection)value) {
                getObjectWithPresetFields(val, presetPaths, getNewPath(path, field.getName()));
            }
        }
    }

    private static void setValue(Object object, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(accessible);
        } catch (Exception e) {
            throw new BpmOrmException(e.getMessage());
        }

    }

    private static Class<?> getGenericTypeOfCollection(Field field) {
        java.lang.reflect.Type[] types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
        if (types != null && types.length > 0) {
            return (Class<?>) types[0];
        }
        return null;
    }

    private static void addPersistedEntitiesToCollection(Collection entityCollection, Collection persistedCollection) {
        if (persistedCollection != null && !persistedCollection.isEmpty()) {
            for (Object persistedVal : persistedCollection) {
                persistedVal = initializeHibernateObject(persistedVal);
                boolean alreadyHas = false;
                for (Object entityVal : entityCollection) {
                    if (ModelUtil.getId(persistedVal).equals(ModelUtil.getId(entityVal))) {
                        alreadyHas = true;
                    }
                }
                if (!alreadyHas) {
                    entityCollection.add(persistedVal);
                }
            }
        }
    }

    private static boolean matchFields(List<String> fields, String root, String field) {
        return hasPath(fields, getNewPath(root, field));
    }

    private static boolean matchFieldsWithRelated(List<String> fields, String root, String field) {
        return hasPathWithRelated(fields, getNewPath(root, field));
    }

    private static String getNewPath(String root, String field) {
        return root == null || "".equals(root) ? field : root + "." + field;
    }

    private static boolean hasPath(List<String> fields, String path) {
        for (String field : fields) {
            if (field.equalsIgnoreCase(path)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPathWithRelated(List<String> fields, String path) {
        for (String field : fields) {
            if (field.equalsIgnoreCase(path) || field.toLowerCase().startsWith(path.toLowerCase() + ".")) {
                return true;
            }
        }
        return false;
    }

}
