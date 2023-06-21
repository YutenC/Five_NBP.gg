package com.shop.shopproduct.dao;

import com.shop.shopproduct.core.CoreDao;
import com.shop.shopproduct.entity.Coupon;

public interface CouponDao extends CoreDao<Coupon,Integer> {
    Coupon selectByDiscountCode(String discountCode);
}
