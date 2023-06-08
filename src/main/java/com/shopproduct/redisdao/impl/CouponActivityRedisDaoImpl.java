package com.shopproduct.redisdao.impl;

import com.google.gson.Gson;
import com.shopproduct.entity.CouponActivity;
import com.shopproduct.redisdao.CouponActivityRedisDao;
import com.shopproduct.util.ConnRedis;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class CouponActivityRedisDaoImpl implements CouponActivityRedisDao {

    @Override
    public void addCouponActivity(CouponActivity couponActivity) {

        Jedis jedis = ConnRedis.getInstance().getJedis();

        String cp_id_str=null;
        System.out.println("couponActivity.getCoupon(): "+couponActivity.getCoupon());
//        if(couponActivity.getCoupon().getId()!=null)
        {
            cp_id_str = jedis.hget("CouponActivity:" + couponActivity.getCoupon().getId().toString(), "cp_id");
        }

        if (cp_id_str == null) {
            Gson gson = new Gson();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("activityName", couponActivity.getActivityName());
            map.put("activityCode", couponActivity.getActivityCode());
            map.put("coupon_id", couponActivity.getCoupon().getId().toString());
            map.put("coupon", gson.toJson(couponActivity.getCoupon()));

            jedis.hmset("CouponActivity:" + couponActivity.getCoupon().getId().toString(), map);

            jedis.sadd("CouponActivity:outline", couponActivity.getCoupon().getId().toString());
        } else {
            System.out.println("重複cp_id");
        }


    }

}
