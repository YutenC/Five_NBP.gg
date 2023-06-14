package com.shopproduct.controller;

import com.google.gson.Gson;
import com.shopproduct.entity.RequestMsg;
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

    public String longTimeProcess(){
        productManagerService.longTimeProcess();
        RequestMsg requestMsg=new RequestMsg("longTime","longTimeProcess","");

        Gson gson=new Gson();
        return gson.toJson(requestMsg);
    }

}