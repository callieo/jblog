package taichitech.common.db;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TtEntityManager {
    public <T> T find(T t) {
        if (!(t instanceof Serializable)) {
            throw new RuntimeException("Object " + t + " is not serializable.");
        }
        return t;
    }

    public TtEntityManager() {
        entityClassMetaDatas = new HashMap<>();
        entities = new ArrayList<>();

    }

    public synchronized String getAutoGenId() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    Collection<Object> entities;
    Map<Class<?>, EntityClassMetaData<?>> entityClassMetaDatas;

    @Value("${filedb.location}")
    private String location;
    private Logger logger = LoggerFactory.getLogger(TtEntityManager.class);

    /**
     * Describe an Entity class, which property is Id, and which properties is
     * Column.
     */
    class EntityClassMetaData<T> {
        private Class<T> type;
        private String idProperty;

        public String getIdProperty() {
            return idProperty;
        }

        public ArrayList<String> getPropertyNames() {
            return propertyNames;
        }

        private ArrayList<String> propertyNames;

        public EntityClassMetaData(Class<T> clazz) {
            propertyNames = new ArrayList<>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Id.class) != null) {
                    idProperty = field.getName();
                }
                if (field.getAnnotation(Column.class) != null) {
                    propertyNames.add(field.getName());
                }
            }
        }
    }

    /** Find ClassMetaData of T, if not exists, create one and return */
    private <T> EntityClassMetaData getClassMetaData(Class<T> clazz) {
        EntityClassMetaData<?> metaData = entityClassMetaDatas.get(clazz);
        if (metaData == null) {
            metaData = new EntityClassMetaData<>(clazz);
            entityClassMetaDatas.put(clazz, metaData);
        }
        return metaData;
    }

    public void save(Object entity) {
        EntityClassMetaData cmd = getClassMetaData(entity.getClass());
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        String idProperty = cmd.getIdProperty();
        if (idProperty == null)
            throw new NullPointerException("Can not find Id property.");
        if (bw.getPropertyValue(idProperty) == null)
            throw new NullPointerException("Id is null.");
        entities.add(entity);
    }

    public <T> T newInstance(Class<T> clazz) throws Exception {
        T entity = BeanUtils.instantiate(clazz);
        EntityClassMetaData<T> classMetaData = getClassMetaData(entity.getClass());
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        beanWrapper.setPropertyValue(classMetaData.getIdProperty(), getAutoGenId());
        entities.add(entity);
        return entity;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5 && i == 3) {
                    break;
                }
                System.out.println("i:" + i + " j:" + j);

            }
        }
    }

    public <T> Collection<T> findByExample(T example) {
        Collection<T> resultSet = new ArrayList<>();
        for (Object entityTmp : entities) {
            if (!entityTmp.getClass().isAssignableFrom(example.getClass())) {
                continue;
            }
            T entity = (T) entityTmp;
            EntityClassMetaData<?> metaData = getClassMetaData(example.getClass());
            ArrayList<String> propertyNames = metaData.getPropertyNames();
            BeanWrapper exampleBw = PropertyAccessorFactory.forBeanPropertyAccess(example);
            BeanWrapper entityBw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
            Object entityIdValue = entityBw.getPropertyValue(metaData.getIdProperty());
            Object exampleIdValue = exampleBw.getPropertyValue(metaData.getIdProperty());
            if ((entityIdValue != null && exampleIdValue != null) && (entityIdValue.equals(exampleIdValue))) {
                resultSet.add(entity);
                continue;
            }
            boolean finded = true;
            boolean onePropertyIsNotNull = false;
            for (String propertyName : propertyNames) {
                Object examplePropertyValue = exampleBw.getPropertyValue(propertyName);
                if (examplePropertyValue == null) {
                    continue;
                }
                onePropertyIsNotNull = true;
                if (!finded) {
                    break;
                }
                // examplePropertyValue !=null
                Object entityPropertyValue = entityBw.getPropertyValue(propertyName);
                if (entityPropertyValue == null) {
                    finded = false;
                }
                if (!examplePropertyValue.equals(entityPropertyValue)) {
                    finded = false;
                }
            }
            if (onePropertyIsNotNull && finded) {
                resultSet.add(entity);
            }
        }
        return resultSet;
    }

    @PreDestroy
    private void destroy() {
        logger.debug("Save entities.");
        try {
            for (Object entity : entities) {
                logger.debug("Save entity {}", StringUtils.abbreviate(entity.toString(), 150));
            }
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dataFile)));
            e.writeObject(entities);
            e.close();
        } catch (Exception e) {
            logger.error("Destory fail", e);
        }
    }

    private File dataFile;

    @PostConstruct
    private void init() throws Exception {
        logger.debug("Init TtEntityManager");
        dataFile = new File(location);
        if (!dataFile.exists()) {
            FileUtils.touch(dataFile);
            logger.debug("Touch new File:{}", dataFile.getAbsolutePath());
        } else {
            XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(dataFile)));
            entities.addAll((Collection<Object>) d.readObject());
            d.close();
        }
        // Init ClassMetaData
        for (Object object : entities) {
            getClassMetaData(object.getClass());
        }
        logger.debug("Init finish");
    }

    private Field findIdFiled(Field[] fields) {
        for (Field field : fields) {
            Id ann = field.getAnnotation(Id.class);
            if (ann instanceof Id) {
                return field;
            }
        }
        return null;
    }

}
