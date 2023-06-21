package com.shopproduct.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shopproduct.entity.Product;
import com.shopproduct.service.ProductService;
import com.shopproduct.util.ObjectInstance;

import java.util.List;

public class ProductController {
    ProductService productService;
    public ProductController(){
        productService=(ProductService) ObjectInstance.getInstance().getObject("ProductService");
    }

    public String getAllProduct() {
        List<Product> products=productService.getAllProduct();





//        Gson gson=new Gson();


//        ExclusionStrategy strategy = new ExclusionStrategy() {
//            @Override
//            public boolean shouldSkipField(FieldAttributes field) {
//                if (field.getDeclaringClass() == MyClass.class && field.getName().equals("other")) {
//                    return true;
//                }
//                if (field.getDeclaringClass() == MySubClass.class && field.getName().equals("otherVerboseInfo")) {
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean shouldSkipClass(Class<?> clazz) {
//                return false;
//            }
//        };


        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String jsonString = gson.toJson(products);


        return jsonString;
    }

}
