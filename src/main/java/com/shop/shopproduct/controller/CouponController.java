package com.shop.shopproduct.controller;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.shop.shopproduct.service.CouponService;

public class CouponController {

    CouponService couponService;

    public CouponController() {

    }


    public String getCoupon(HttpSession session) {
        System.out.println("getCoupon");

        Gson gson = new Gson();

        String json_str = gson.toJson(couponService.getCouponById(1));

        return json_str;
    }


    public String getCouponByActivity(HttpSession session, String activityCode) {
        System.out.println("getCouponByActivity");

        Gson gson = new Gson();
        String json_str = gson.toJson(couponService.getCouponById(1));

        return json_str;
    }
}
