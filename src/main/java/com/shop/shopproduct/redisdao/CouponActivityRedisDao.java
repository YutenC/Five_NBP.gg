package com.shop.shopproduct.redisdao;

import java.util.List;

import com.shop.shopproduct.entity.CouponActivity;

public interface CouponActivityRedisDao {

    List<CouponActivity> getAllCouponActivity();
    List<String> getAllCouponActivity_string();
    void addCouponActivity(CouponActivity couponActivity);
    void  updateCouponActivity(CouponActivity couponActivity);
    void deleteCouponActivity(Integer couponId);


}
