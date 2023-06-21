package com.shop.shoporder.dao;

import java.util.List;

import com.core.util.JedisUtil;
import com.shop.shoporder.entity.ShoppingList;

import redis.clients.jedis.Jedis;

public interface JedisShoppingListDao {
	
	boolean insert(ShoppingList shoppingList);
	
	boolean delete(ShoppingList shoppingList);
	
	boolean update(ShoppingList shoppingList);
	
	List<ShoppingList> selectByMemberId(Integer memberId);
	
	default Jedis getJedis() {
		return JedisUtil.getJedisPool().getResource();
	}
}
