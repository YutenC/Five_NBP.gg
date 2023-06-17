package com.shopproduct.service;

import com.shopproduct.entity.CouponActivity;

import java.util.List;

public interface CouponManagerService {
    void generateCouponActivity();
    void addCouponActivity(CouponActivity couponActivity);

    List<CouponActivity> getAllCouponActivity();
    List<CouponActivity> searchCouponActivity();

    void updateCouponActivity(CouponActivity couponActivity);


    boolean deleteCoupon(Integer couponId);

}
