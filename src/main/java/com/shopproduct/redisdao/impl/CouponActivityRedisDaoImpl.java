package com.shopproduct.redisdao.impl;

import com.google.gson.Gson;
import com.shopproduct.entity.CouponActivity;
import com.shopproduct.redisdao.CouponActivityRedisDao;
import com.shopproduct.util.RedisFactory;
import com.shopproduct.util.ShopProductConst;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;

public class CouponActivityRedisDaoImpl implements CouponActivityRedisDao {

    @Override
    public List<String> getAllCouponActivity() throws JedisException {
        List<String> couponActivities = new ArrayList<String>();
//        Jedis jedis = ConnRedis.getInstance().getJedis();
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        Gson gson = new Gson();
        Set<String> items = jedis.smembers("CouponActivity:outline");
        for (String str : items) {
            Map<String, String> couponActivityMap = jedis.hgetAll("CouponActivity:" + str);
            String json=gson.toJson(couponActivityMap);
            couponActivities.add(json);
        }

        return couponActivities;
    }

    @Override
    public void addCouponActivity(CouponActivity couponActivity) {

//        Jedis jedis = ConnRedis.getInstance().getJedis();
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
            map.put("couponId", couponActivity.getCoupon().getId().toString());
            map.put("coupon", gson.toJson(couponActivity.getCoupon()));

            jedis.hmset("CouponActivity:" + couponActivity.getCoupon().getId().toString(), map);

            jedis.sadd("CouponActivity:outline", couponActivity.getCoupon().getId().toString());
        } else {
            System.out.println("重複cp_id");
        }


    }

    @Override
    public void deleteCouponActivity(Integer couponId) {
//        Jedis jedis = ConnRedis.getInstance().getJedis();
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
        jedis.close();
    }

}
