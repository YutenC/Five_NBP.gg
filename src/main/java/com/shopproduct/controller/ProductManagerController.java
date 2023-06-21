package com.shopproduct.controller;

import com.google.gson.Gson;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.entity.RequestMsg;
import com.shopproduct.pojo.ProductPojo;
import com.shopproduct.service.CouponManagerService;
import com.shopproduct.service.ProductManagerService;
import com.shopproduct.util.ObjectInstance;

import java.util.List;

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

    public void addProduct(ProductPojo productPojo){
        productManagerService.addProduct(productPojo);
    }

    public String getSomeProduct(){
return null;
    }

}
