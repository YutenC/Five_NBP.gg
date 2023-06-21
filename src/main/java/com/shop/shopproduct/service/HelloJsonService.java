package com.shop.shopproduct.service;

import com.shop.shopproduct.entity.Coupon;

public interface HelloJsonService {

    void addCoupon(Coupon coupon);
    String getAllCoupon();

    String getCoupon(Integer id);

    void deleteCoupon(Integer coupon_id);
}
