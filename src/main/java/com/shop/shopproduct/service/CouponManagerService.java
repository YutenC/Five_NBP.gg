package com.shop.shopproduct.service;

import java.util.List;

import com.shop.shopproduct.entity.CouponActivity;

public interface CouponManagerService {
    void generateCouponActivity();
    void addCouponActivity(CouponActivity couponActivity);

    List<CouponActivity> getAllCouponActivity();
    List<CouponActivity> searchCouponActivity();

    void updateCouponActivity(CouponActivity couponActivity);


    boolean deleteCoupon(Integer couponId);

}
