package com.shop.shopproduct.util;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StringToObjectUtil {


//    private boolean checkJAVAType(Class<?> type) {
//        if (type == Integer.class || type == String.class ||
//                type == Double.class || type == java.util.Date.class ||
//                type == Long.class || type == Float.class ||
//                type == Byte.class || type == Short.class) {
//            return true;
//        }
//        return false;
//    }

    private static Object stringToType(String str, Class<?> type) {
        if (type == Integer.class) {
            return Integer.valueOf(str);
        } else if (type == String.class) {
            return str;
        } else if (type == Double.class) {
            return Double.valueOf(str);
        } else if (type == Long.class) {
            return Long.valueOf(str);
        } else if (type == Float.class) {
            return Float.valueOf(str);
        } else if (type == Byte.class) {
            return Byte.valueOf(str);
        } else if (type == Short.class) {
            return Short.valueOf(str);
        } else if (type == java.util.Date.class) {

        }

        return null;
    }

    public static  <T> T mapStringToEntity(Map<String, String> map, Class<T> class_) {

        try {
            T object =(T) class_.getDeclaredConstructor().newInstance();
            map.forEach((k, v) -> {
                try {
                    Field field = class_.getDeclaredField(k);
                    field.setAccessible(true);

                    Class<?> type = field.getType();
                    Object value;

                    if ((value = stringToType(v, type)) == null) {
                        Gson gson = new Gson();
                        value = gson.fromJson(v, type);
                    }

                    field.set(object, value);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            });

            return object;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

}
