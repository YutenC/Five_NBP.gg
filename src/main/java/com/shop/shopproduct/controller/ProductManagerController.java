package com.shop.shopproduct.controller;

import com.google.gson.Gson;
import com.shop.shopproduct.entity.RequestMsg;
import com.shop.shopproduct.pojo.ProductPojo;
import com.shop.shopproduct.service.ProductManagerService;
import com.shop.shopproduct.util.ObjectInstance;

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
