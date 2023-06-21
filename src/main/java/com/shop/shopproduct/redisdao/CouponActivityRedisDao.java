package com.shopproduct.redisdao;

import com.shopproduct.entity.CouponActivity;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;

public interface CouponActivityRedisDao {

    List<CouponActivity> getAllCouponActivity();
    List<String> getAllCouponActivity_string();
    void addCouponActivity(CouponActivity couponActivity);
    void  updateCouponActivity(CouponActivity couponActivity);
    void deleteCouponActivity(Integer couponId);


}
