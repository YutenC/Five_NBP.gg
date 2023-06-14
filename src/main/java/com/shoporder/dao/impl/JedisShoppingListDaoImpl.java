package com.shoporder.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.core.util.JedisUtil;
import com.shoporder.dao.JedicShoppingListDao;
import com.shoporder.entity.PKShoppingList;
import com.shoporder.entity.ShoppingList;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisShoppingListDaoImpl implements JedicShoppingListDao{

	private Jedis jedis;
	
	public JedisShoppingListDaoImpl () {
		jedis = JedisUtil.getJedisPool().getResource();
	}

	@Override
	public boolean insert(ShoppingList shoppingList) {
		Transaction jediTx = jedis.multi();
		String key = "member:" + shoppingList.getPkShoppingList().getMemmberId() + ":shoppingList";
		Map<String, String> detail = new HashMap<>();
		String productId = "product:" + shoppingList.getPkShoppingList().getProductId();
		String buyAmount = shoppingList.getQuantity().toString();
		detail.put(productId, buyAmount);
		jediTx.hmset(key, detail);
		jediTx.exec();
		jedis.close();
		return true;
	}

	@Override
	public boolean delete(ShoppingList shoppingList) {
		Transaction jediTx = jedis.multi();
		String key = "member:" + shoppingList.getPkShoppingList().getMemmberId() + ":"
					+ "product" + shoppingList.getPkShoppingList().getProductId();
		jediTx.del(key);
		jediTx.exec();
		jedis.close();
		return true;
	}

	@Override
	public boolean update(ShoppingList shoppingList) {
		Transaction jediTx = jedis.multi();
		String key = "member:" + shoppingList.getPkShoppingList().getMemmberId();
		String productId = "product:" + shoppingList.getPkShoppingList().getProductId();
		Integer buyAmount = shoppingList.getQuantity();
		jediTx.hincrBy(key, productId, buyAmount);
		jediTx.exec();
		jedis.close();
		return true;
	}

	@Override
	public List<ShoppingList> selectByMemberId(Integer memberId) {
		List<ShoppingList> result = new ArrayList<>();
		String key = "member:" + memberId;
		Map<String, String> detail = jedis.hgetAll(key);
		for (String product : detail.keySet()) {
			PKShoppingList pkspList = new PKShoppingList();
			pkspList.setMemmberId(memberId);
			pkspList.setProductId(Integer.valueOf(product.split(":")[1]));
			ShoppingList spList = new ShoppingList();
			spList.setPkShoppingList(pkspList);
			spList.setQuantity(Integer.valueOf(detail.get(product)));
			result.add(spList);
		}
		return result;
	}

	
}
