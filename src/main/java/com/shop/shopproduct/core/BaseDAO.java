package com.shop.shopproduct.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public abstract class BaseDAO<T> {

    private Class entityClass;

    public BaseDAO() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        try {
            entityClass = Class.forName(types[0].getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        try {
            return ConnDBUtil.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void setParams(PreparedStatement statement, Object... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setValue(T object, String property, Object propertyValue) {
        Class objectClass = object.getClass();

        try {

            Field field = objectClass.getDeclaredField(property);

            if (("class java.time.LocalDateTime").equals(propertyValue.getClass().toString())) {
                propertyValue =Timestamp.valueOf((LocalDateTime)propertyValue);
            }

            if (field != null) {
                field.setAccessible(true);
                field.set(object, propertyValue);
            }

        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected void processOneColumn(String sql, Object... params) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            setParams(statement, params);
            int row = statement.executeUpdate();// .execute();
            System.out.println(row + " row(s) inserted!!");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void processBatchColumn(String sql, Object[][] params) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                setParams(statement, params[i]);
                statement.addBatch();
            }

            int[] counts = statement.executeBatch();
            int sum = 0;
            for (int count : counts) {
                sum += count;
            }

            System.out.println(sum + " row(s) inserted!!");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected T query(String sql, Object... params) {
        T entity = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            setParams(statement, params);
            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int colNum = metaData.getColumnCount();

            if (resultSet.next()) {
                entity = (T) entityClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < colNum; i++) {
                    String name = metaData.getColumnName(i + 1).toLowerCase();
                    Object objValue = resultSet.getObject(i + 1);
                    setValue(entity, name, objValue);
                }
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return entity;
    }

    protected List<T> querySpecificData(String sql, Object... params) {
        List<T> entitys = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            setParams(statement, params);
            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int colNum = metaData.getColumnCount();

            entitys = new ArrayList<T>();

            while (resultSet.next()) {
                T entity = (T) entityClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < colNum; i++) {
                    String name = metaData.getColumnName(i + 1).toLowerCase();
                    Object objValue = resultSet.getObject(i + 1);
//					System.out.println(name);
//					System.out.println(objValue);
//					System.out.println(objValue.toString());
//					System.out.println(objValue.getClass());

                    setValue(entity, name, objValue);
                }

                entitys.add(entity);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return entitys;
    }

}