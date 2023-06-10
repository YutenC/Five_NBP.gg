package com.shopproduct.redisdao;

import com.shopproduct.entity.CouponActivity;

import java.util.List;

public interface CouponActivityRedisDao {


    List<String> getAllCouponActivity();
    void addCouponActivity(CouponActivity couponActivity);
    void deleteCouponActivity(Integer couponId);


}
