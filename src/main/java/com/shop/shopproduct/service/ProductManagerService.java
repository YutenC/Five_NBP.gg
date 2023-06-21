package com.shop.shopproduct.service;

import com.shop.shopproduct.pojo.ProductPojo;

public interface ProductManagerService {

    void createProductFromcsv();

    void longTimeProcess();

    void addProduct(ProductPojo productPojo);
}
