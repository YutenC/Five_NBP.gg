package com.shopproduct.service;

import com.shopproduct.entity.CouponActivity;

import java.util.List;

public interface CouponManagerService {
    void generateCouponActivity();
    void addCouponActivity(CouponActivity couponActivity);

    List<String> getAllCouponActivity();
    List<CouponActivity> searchCouponActivity();

    boolean deleteCoupon(Integer couponId);

}