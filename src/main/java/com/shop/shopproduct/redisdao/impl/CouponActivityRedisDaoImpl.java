package com.shop.shopproduct.redisdao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.shop.shopproduct.entity.CouponActivity;
import com.shop.shopproduct.redisdao.CouponActivityRedisDao;
import com.shop.shopproduct.util.RedisFactory;
import com.shop.shopproduct.util.ShopProductConst;
import com.shop.shopproduct.util.StringToObjectUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

public class CouponActivityRedisDaoImpl implements CouponActivityRedisDao {

    @Override
    public List<String> getAllCouponActivity_string() throws JedisException {
        List<String> couponActivities = new ArrayList<String>();
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        Gson gson = new Gson();
        Set<String> items = jedis.smembers("CouponActivity:outline");
        for (String str : items) {
            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
            String json=gson.toJson(couponActivityMap);
            couponActivities.add(json);
        }
//        jedis.close();
        return couponActivities;
    }

    @Override
    public List<CouponActivity> getAllCouponActivity() throws JedisException {
        List<CouponActivity> couponActivities = new ArrayList<CouponActivity>();
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        Gson gson = new Gson();
        Set<String> items = jedis.smembers("CouponActivity:outline");
//        for (String str : items) {
//            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
//            String json=gson.toJson(couponActivityMap);
//            couponActivities.add(json);
//        }

        for (String str : items) {
            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
            CouponActivity couponActivity= StringToObjectUtil.mapStringToEntity(couponActivityMap,CouponActivity.class);
            couponActivities.add(couponActivity);
        }
//        jedis.close();
        return couponActivities;
//        return couponActivities;
    }

    @Override
    public void addCouponActivity(CouponActivity couponActivity) {
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        String cp_id_str=null;
        System.out.println("couponActivity.getCoupon(): "+couponActivity.getCoupon());
//        if(couponActivity.getCoupon().getId()!=null)
        {
            cp_id_str = jedis.hget("CouponActivity:" + couponActivity.getCoupon().getId().toString(), "couponId");
        }

        if (cp_id_str == null) {
            Gson gson = new Gson();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("activityName", couponActivity.getActivityName());
            map.put("activityCode", couponActivity.getActivityCode());
//            map.put("couponId", couponActivity.getCoupon().getId());
//            map.put("coupon", couponActivity.getCoupon());
            map.put("couponId", couponActivity.getCoupon().getId().toString());
            map.put("coupon", gson.toJson(couponActivity.getCoupon()));

            jedis.hmset("CouponActivity:" + couponActivity.getCoupon().getId().toString(), map);

            jedis.sadd("CouponActivity:outline", couponActivity.getCoupon().getId().toString());
        } else {
            System.out.println("重複cp_id");
        }
//        jedis.close();

    }

    @Override
    public void updateCouponActivity(CouponActivity couponActivity) {
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        String cp_id_str=null;
        System.out.println("couponActivity.getCoupon(): "+couponActivity.getCoupon());
//        if(couponActivity.getCoupon().getId()!=null)
        {
            cp_id_str = jedis.hget("CouponActivity:" + couponActivity.getCoupon().getId().toString(), "couponId");
        }

        if (cp_id_str != null) {
            Gson gson = new Gson();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("activityName", couponActivity.getActivityName());
            map.put("activityCode", couponActivity.getActivityCode());
//            map.put("couponId", couponActivity.getCoupon().getId());
//            map.put("coupon", couponActivity.getCoupon());
            map.put("couponId", couponActivity.getCoupon().getId().toString());
            map.put("coupon", gson.toJson(couponActivity.getCoupon()));

            jedis.hmset("CouponActivity:" + couponActivity.getCoupon().getId().toString(), map);

            jedis.sadd("CouponActivity:outline", couponActivity.getCoupon().getId().toString());
        } else {
//            System.out.println("重複cp_id");
        }
//        jedis.close();
    }

    @Override
    public void deleteCouponActivity(Integer couponId) {
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        Set<String> items = jedis.smembers("CouponActivity:outline");
        for (String str : items) {
            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
            int field=Integer.parseInt(couponActivityMap.get("couponId"));

            System.out.println("field: "+field);
            System.out.println("couponId: "+couponId);
            if(field==(couponId))
            {
                jedis.del("CouponActivity:" + str);
                jedis.srem("CouponActivity:outline",str);
            }

        }
//        jedis.close();
    }

}
