package com.shop.shopproduct.service;

import java.util.List;

import com.shop.shopproduct.entity.Coupon;

public interface CouponService {
    List<Coupon> getAllCoupon();
    Coupon getCouponById(Integer id);
    Coupon getCouponByDiscountCode(String  discountCode);

    int addCoupon(Coupon coupon);
    boolean deleteCoupon(Integer id);
}
