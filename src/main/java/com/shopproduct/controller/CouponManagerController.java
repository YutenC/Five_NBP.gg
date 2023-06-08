package com.shopproduct.controller;

import com.google.gson.Gson;
import com.shopproduct.entity.CouponActivity;
import com.shopproduct.service.CouponManagerService;
import com.shopproduct.util.ObjectInstance;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CouponManagerController {
    CouponManagerService couponManagerService;

    public  CouponManagerController(){
        couponManagerService=(CouponManagerService) ObjectInstance.getInstance().getObject("CouponManagerService");
    }

    public String getAllCouponActivity(HttpSession session) {
        System.out.println("getAllCouponActivity_json");
        List<String> out_json = couponManagerService.getAllCouponActivity();
        return out_json.toString();
    }

    public String addCouponActivity(HttpSession session,String newCouponActivity) {
        System.out.println("addCouponActivity");

        Gson gson=new Gson();
        CouponActivity couponActivity=gson.fromJson(newCouponActivity, CouponActivity.class);

        couponManagerService.addCouponActivity(couponActivity);
        return "";
    }

    public String generateCouponActivity() {
        System.out.println("generateCouponActivity");
        couponManagerService.generateCouponActivity();
        return "npb/couponManager";
    }

    public String deleteCoupon(Integer id) {
        System.out.println("deleteCoupon");
        couponManagerService.deleteCoupon(id);
        return "";
    }


}
