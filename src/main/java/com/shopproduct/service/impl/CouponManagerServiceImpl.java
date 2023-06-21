package com.shopproduct.service.impl;

import com.google.gson.Gson;
import com.shopproduct.dao.ProductDao;
import com.shopproduct.dao.impl.ProductDaoImpl;
import com.shopproduct.entity.Coupon;
import com.shopproduct.entity.CouponActivity;
import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.redisdao.CouponActivityRedisDao;
import com.shopproduct.service.CouponManagerService;
import com.shopproduct.service.CouponService;
import com.shopproduct.util.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.text.SimpleDateFormat;
import java.util.*;

public class CouponManagerServiceImpl implements CouponManagerService {

    CouponService couponService;
    CouponActivityRedisDao couponActivityRedisDao;

    public CouponManagerServiceImpl() {
        couponService = (CouponService) ObjectInstance.getInstance().getObject("CouponService");
        couponActivityRedisDao = (CouponActivityRedisDao) ObjectInstance.getInstance().getObject("CouponActivityRedisDao");
    }

    @Override
    public void generateCouponActivity() {
        for (int i = 0; i < 10; i++) {
            CouponActivity couponActivity = new CouponActivity("Activity " + i, "MF");
            couponActivity.setCoupon(genCouponData());
            addCouponActivity(couponActivity);
        }
    }

    @Override
    public void addCouponActivity(CouponActivity couponActivity) {
        Coupon coupon = couponActivity.getCoupon();
        couponService.addCoupon(coupon);

        RedisContent redisService = new RedisContent() {
            @Override
            public int run() {
//                System.out.println("coupon.getDiscountCode(): "+coupon.getDiscountCode());
                Coupon coupon_=couponService.getCouponByDiscountCode(coupon.getDiscountCode());
                couponActivity.setCoupon(coupon_);
                couponActivityRedisDao.addCouponActivity(couponActivity);
                return 0;
            }
        };
        RedisFactory.getRedisServiceInstance().registerRedisService(redisService);
    }

    @Override
    public List<CouponActivity> getAllCouponActivity() throws RuntimeException {
//        List<String> couponActivityMap_json = new ArrayList<String>();
//        Jedis jedis = ConnRedis.getInstance().getJedis();
//
//        Gson gson = new Gson();
//        Set<String> items = jedis.smembers("CouponActivity:outline");
//        for (String str : items) {
//            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
//            couponActivityMap_json.add(gson.toJson(couponActivityMap));
//        }



        return  couponActivityRedisDao.getAllCouponActivity();
    }

    @Override
    public List<CouponActivity> searchCouponActivity() {
        return null;
    }

    @Override
    public boolean deleteCoupon(Integer couponId) {
        if(couponService.getCouponById(couponId)!=null){
            couponService.deleteCoupon(couponId);
        }



        RedisContent redisService = new RedisContent() {
            @Override
            public int run() {
//                System.out.println("coupon.getDiscountCode(): "+coupon.getDiscountCode());
//                Coupon coupon_=couponService.getCouponByDiscountCode(coupon.getDiscountCode());
//                couponActivity.setCoupon(coupon_);
                couponActivityRedisDao.deleteCouponActivity(couponId);
                return 0;
            }
        };
        RedisFactory.getRedisServiceInstance().registerRedisService(redisService);

        return true;
    }


    private Coupon genCouponData() {
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
