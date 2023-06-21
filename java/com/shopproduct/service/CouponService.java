package com.shopproduct.service;

import com.shopproduct.entity.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupon();
    Coupon getCouponById(Integer id);
    Coupon getCouponByDiscountCode(String  discountCode);

    int addCoupon(Coupon coupon);
    boolean deleteCoupon(Integer id);
}
