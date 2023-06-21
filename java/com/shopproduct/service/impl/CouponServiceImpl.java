package com.shopproduct.service.impl;

import com.shopproduct.dao.CouponDao;
import com.shopproduct.entity.Coupon;
import com.shopproduct.service.CouponService;
import com.shopproduct.util.ObjectInstance;

import java.util.List;

public class CouponServiceImpl implements CouponService {
    CouponDao couponDao;

    public CouponServiceImpl(){
        couponDao=(CouponDao) ObjectInstance.getInstance().getObject("CouponDao");
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponDao.selectAll();
    }

    @Override
    public Coupon getCouponById(Integer id) {
        Coupon coupon=couponDao.selectById(id);


//        Gson gson=new Gson();
        return coupon;
    }

    @Override
    public Coupon getCouponByDiscountCode(String discountCode) {

        return couponDao.selectByDiscountCode(discountCode);
    }

    @Override
    public int addCoupon(Coupon coupon) {
//        Coupon coupon=genCouponData();


        return couponDao.insert(coupon);
    }

    @Override
    public boolean deleteCoupon(Integer id) {
        couponDao.deleteById(id);
        return false;
    }






}
