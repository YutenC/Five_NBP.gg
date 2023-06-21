package com.shop.shopproduct.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.shopproduct.entity.Product;
import com.shop.shopproduct.entity.ProductDetail;
import com.shop.shopproduct.service.ProductService;
import com.shop.shopproduct.util.ObjectInstance;

import java.util.List;

public class ProductController {
    ProductService productService;

    public ProductController() {
        productService = (ProductService) ObjectInstance.getInstance().getObject("ProductService");
    }

    public String getAllProduct() {
        List<Product> products = productService.getAllProduct();


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


//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .create();
//        String jsonString = gson.toJson(products);


        return toJson(products);

    }

    public String getProductById(Integer id) {
        Product product = productService.getProductById(id);
        return toJson(product);
    }

    public String getProductDetail(Integer id) {
        ProductDetail productDetail = productService.getProductDetail(id);
        productService.saveProductBrowseToRedis(id);
        return toJson(productDetail);
    }


    public String getProductHistory() {

        List<Product> products=  productService.getProductHistory();

        return toJson(products);
    }


    private String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(obj);
    }

}
