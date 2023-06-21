package com.shopproduct.service;

import com.shopproduct.pojo.ProductPojo;

public interface ProductManagerService {

    void createProductFromcsv();

    void longTimeProcess();

    void addProduct(ProductPojo productPojo);
}
