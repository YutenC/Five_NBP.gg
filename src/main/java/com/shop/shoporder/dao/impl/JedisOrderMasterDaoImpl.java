package com.shop.shoporder.dao.impl;

import java.util.Map;

import com.shop.shoporder.dao.JedisOrderMasterDao;

import redis.clients.jedis.Jedis;

public class JedisOrderMasterDaoImpl implements JedisOrderMasterDao{

	@Override
	public boolean saveOrderMasterResults(String key, String content) {
		Jedis jedis = getJedis();
		jedis.select(3);
		jedis.set(key, content);
		jedis.close();
		return true;
	}

	@Override
	public String getResults(String key) {
		Jedis jedis = getJedis();
		jedis.select(3);
		String results = jedis.get(key);
		jedis.close();
		return results;
	}
	
}
