package com.shopproduct.controller;

import com.google.gson.Gson;
import com.shopproduct.service.CouponService;

import javax.servlet.http.HttpSession;

public class CouponController {

    CouponService couponService;


    public void generateCoupon() {
//        couponService.addCoupon();
    }


    public String getCoupon_json(HttpSession session) {
        System.out.println("getCoupon_json");

        Gson gson=new Gson();

        String json_str=gson.toJson(couponService.getCouponById(1));

        return json_str;
    }


    public String getCouponByActivity_json(HttpSession session,String activityCode) {
        System.out.println("getCouponByActivity_json");

        Gson gson=new Gson();
        String json_str=gson.toJson(couponService.getCouponById(1));

        return json_str;
    }
}
