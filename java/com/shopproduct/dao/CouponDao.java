package com.shopproduct.dao;

import com.shopproduct.core.CoreDao;
import com.shopproduct.entity.Coupon;

public interface CouponDao extends CoreDao<Coupon,Integer> {
    Coupon selectByDiscountCode(String discountCode);
}
