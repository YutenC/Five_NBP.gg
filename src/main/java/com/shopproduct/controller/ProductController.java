package com.shopproduct.controller;

import com.google.gson.Gson;
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

        Gson gson=new Gson();

        return gson.toJson(products);
    }

}
