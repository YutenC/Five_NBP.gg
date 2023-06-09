package com.shopproduct.util;

import com.shopproduct.dao.ProductDao;
import com.shopproduct.dao.ProductImageDao;
import com.shopproduct.dao.impl.ProductDaoImpl;
import com.shopproduct.dao.impl.ProductImageDaoImpl;
import com.shopproduct.entity.ProductImage;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CreateProductDB<T, P> {

//    final String csvPath = "..\\..\\..\\..\\webapp\\img\\gameSoftware\\test\\origin\\假資料.csv";
//    final String srcImgPath = "..\\..\\..\\..\\webapp\\img\\gameSoftware\\test\\origin\\";
//    final String desImgPath = "..\\..\\..\\..\\webapp\\img\\gameSoftware\\test\\";
//    final String csvPath = "src\\main\\webapp\\img\\gameSoftware\\test\\origin\\假資料.csv";
//    final String srcImgPath = "src\\main\\webapp\\img\\gameSoftware\\test\\origin\\";
//    final String desImgPath = "src\\main\\webapp\\img\\gameSoftware\\test\\";

    final String csvPath = "C:\\MyWorkSpace\\five\\update\\Five_NBP.gg\\src\\main\\webapp\\img\\gameSoftware\\test\\origin\\假資料.csv";
    final String srcImgPath = "C:\\MyWorkSpace\\five\\update\\Five_NBP.gg\\src\\main\\webapp\\img\\gameSoftware\\test\\origin\\";
    final String desImgPath = "C:\\MyWorkSpace\\five\\update\\Five_NBP.gg\\src\\main\\webapp\\img\\gameSoftware\\test\\";

    ProductDao productDao;
    ProductImageDao productImageDao;
    private Class entityClass;
    private Class entityClass_P;

    public CreateProductDB(Class c, Class p) {
        entityClass = c;
        entityClass_P = p;

        productDao = new ProductDaoImpl();
        productImageDao = new ProductImageDaoImpl();
    }

    public List<T> readCSV() throws NoSuchFieldException, IllegalAccessException {
        String csvFile = csvPath;
        String line;
        String csvDelimiter = ",";

        List<String> meta = new ArrayList<>();
        List<T> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);
                for (String value : data) {
                    meta.add(value);
                }
            }

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);

                if (data.length > 1) {
                    T entity = null;
                    entity = (T) entityClass.getDeclaredConstructor().newInstance();

                    // Process the data from the CSV file
                    for (int i = 0; i < data.length; i++) {
                        setValue(entity, meta.get(i), data[i]);
                    }

                    products.add(entity);
                } else {
                }

                System.out.println();
            }

            for (T value : products) {
                System.out.println(value + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

//        T  product= products.get(0);
//        Field field = product.getClass().getDeclaredField("productName");
//        field.setAccessible(true);
//        System.out.println("field: "+field.get(product));
        return products;
    }

    public List<P> createImgEntity() throws NoSuchFieldException, IllegalAccessException {
        List<String> fileNames = getListFile(srcImgPath);


        String newFileName = null;
        File file;
        String pathName;
        List<T> products = (List<T>) productDao.selectAll();

        for (int i = 0; i < products.size(); i++) {
            T product = products.get(i);

            Field field = product.getClass().getDeclaredField("productName");
            field.setAccessible(true);
            String fileName = (String) field.get(product);

            field = product.getClass().getDeclaredField("id");
            field.setAccessible(true);
            Integer p_id = (Integer) field.get(product);


            for (int j = 0; j < 3; j++) {
                if (j != 0) {
                    pathName = srcImgPath + fileName + "_" + j + ".PNG";
                    newFileName = p_id + "_" + j + ".PNG";
                } else {
                    pathName = srcImgPath + fileName + ".PNG";
                    newFileName = p_id + "_index" + ".PNG";
                }

                file = new File(pathName);
                if (!file.exists()) {
                    continue;
                }

                // Check if the file exists
                if (file.exists()) {

                    File file2 = new File(desImgPath + newFileName);
                    copyFile(file, file2);
//                    if(!file2.exists()){
//                        copyFile(file, file2);
//                    }


                    // Rename the file
//                    File newFile = new File(file.getParent(), newFileName);
//                    if (file.renameTo(newFile)) {
//                        System.out.println("File renamed successfully.");
//                    } else {
//                        System.out.println("Failed to rename the file.");
//                    }
                } else {
                    System.out.println("File does not exist.");
                }


                ProductImage productImage = new ProductImage(p_id, "./img/gameSoftware/test/" + newFileName);
                productImageDao.insert(productImage);

            }

        }

        return null;
    }

    public void copyFile(File file1, File file2) {

        try {

            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);

            BufferedInputStream BufferInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream BufferOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer2 = new byte[1024];

            while (BufferInputStream.read(buffer2) != -1) {
                BufferOutputStream.write(buffer2);
            }

            BufferOutputStream.close();
            BufferInputStream.close();
            fileInputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> getListFile(String dirPath) {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(dirPath); // 目錄物件
        System.out.println(("File Name: " + directory.getName()));
        System.out.println(("File Path: " + directory.getPath()));
        System.out.println(("File getAbsolutePath: " + directory.getAbsolutePath()));
        // Check if the specified path is a directory
        if (directory.isDirectory()) {
            // Get all files in the directory
            File[] files = directory.listFiles();

            // Iterate over the files and print their names
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;

    }

    private void setValue(T object, String property, Object propertyValue) {
        Class objectClass = object.getClass();

        try {
            Field field = objectClass.getDeclaredField(property);
            if (("java.util.Date").equals(field.getType().getName())) {
                GregorianCalendar cal = new GregorianCalendar();
                propertyValue = cal.getTime();
            } else if (("java.lang.Integer").equals(field.getType().getName())) {
                propertyValue = Integer.valueOf((String) propertyValue);
            }

            if ("type".equals(property)) {
                String type_ = (String) propertyValue;
                type_ = type_.trim();
                switch (type_) {
                    case "PS5"://22
                        propertyValue = "1";//22
                        break;
                    case "Switch"://02
                        propertyValue = "2";//02
                        break;
                }
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


}
