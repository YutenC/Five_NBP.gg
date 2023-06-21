package com.shop.shopproduct.service.impl;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gson.Gson;
import com.shop.shopproduct.dao.CouponDao;
import com.shop.shopproduct.entity.Coupon;
import com.shop.shopproduct.service.HelloJsonService;
import com.shop.shopproduct.util.HelloInstance;

public class HelloJsonServiceImpl implements HelloJsonService {

    CouponDao couponDao;

    public HelloJsonServiceImpl(){
        couponDao= (CouponDao)HelloInstance.getInstance().getObject("CouponDao");
    }

    @Override
    public void addCoupon(Coupon coupon) {
        couponDao.insert(coupon);
    }

    @Override
    public String getAllCoupon() {
        List<Coupon> coupons=  couponDao.selectAll();

        if(coupons==null||coupons.size()==0){
            for(int i=0;i<5;i++){
                addCoupon(genCouponData());
            }
            coupons=  couponDao.selectAll();
        }

        Gson gson = new Gson();
        String coupons_json=gson.toJson(coupons);
        return coupons_json;
    }

    @Override
    public String getCoupon(Integer id) {
        Coupon coupon=  couponDao.selectById(id);
        Gson gson = new Gson();
        String coupons_json=gson.toJson(coupon);

        return coupons_json;
    }

    @Override
    public void deleteCoupon(Integer id) {
        couponDao.deleteById(id);
    }


    private Coupon genCouponData(){
        Integer discount = ((int) (Math.random() * 50 + 51));
        Integer condition_price = ((int) (Math.random() * 500 + 501));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(cal.getTimeInMillis() + 10 * 24 * 60 * 60 * 1000);
        String date = simpleDateFormat.format(cal.getTime());
        java.sql.Date deadline = java.sql.Date.valueOf(date);

        String discountCode = genCouponCode();
        System.out.println("discountCode: " + discountCode);
        Coupon coupon = new Coupon(discount, condition_price, deadline, discountCode);

        return coupon;
    }

    private String genCouponCode() {
        final int RANDOM_NUM = 8;
        int rangeNum = 62;
        char[] randomChar = new char[RANDOM_NUM];
        String str = "";
        for (int i = 0; i < RANDOM_NUM; i++) {
            int random = (int) (Math.random() * rangeNum);
            if (random < 10) {
                randomChar[i] = (char) ('0' + random);
            } else if (random < 36) {
                randomChar[i] = (char) ('A' + random - 10);
            } else if (random >= 36) {
                randomChar[i] = (char) ('a' + random - 36);
            } else {

            }
            str += randomChar[i];
        }
        return str;
    }

}
