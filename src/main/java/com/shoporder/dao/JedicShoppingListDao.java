package com.shoporder.dao;

import java.util.List;

import com.shoporder.entity.ShoppingList;

public interface JedicShoppingListDao {
	
	boolean insert(ShoppingList shoppingList);
	
	boolean delete(ShoppingList shoppingList);
	
	boolean update(ShoppingList shoppingList);
	
	List<ShoppingList> selectByMemberId(Integer memberId);
}
