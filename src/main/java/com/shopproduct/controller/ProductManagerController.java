package com.shopproduct.controller;

import com.shopproduct.service.CouponManagerService;
import com.shopproduct.service.ProductManagerService;
import com.shopproduct.util.ObjectInstance;

public class ProductManagerController {

    ProductManagerService productManagerService;
    public ProductManagerController() {
        productManagerService=(ProductManagerService) ObjectInstance.getInstance().getObject("ProductManagerService");
    }


    public void createProductFromcsv() {
        productManagerService.createProductFromcsv();
    }

}
