package com.shop.shoporder.dao;

import java.util.Map;

import com.core.util.JedisUtil;

import redis.clients.jedis.Jedis;

public interface JedisOrderMasterDao {
	
	default Jedis getJedis() {
		return JedisUtil.getJedisPool().getResource();
	}
	
	boolean saveOrderMasterResults(String key, String content);
	
	String getResults(String key);
}
