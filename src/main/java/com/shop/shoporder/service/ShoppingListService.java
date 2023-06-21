package com.shop.shoporder.service;

import java.util.List;

import com.core.service.CoreService;
import com.shop.shoporder.entity.ShoppingList;
import com.shop.shoporder.util.TransOrderProduct;

public interface ShoppingListService extends CoreService{
	
	List<TransOrderProduct> getAllShoppingList(Integer memberId);
	
	boolean addOneShoppingList(TransOrderProduct trpd, Integer memberId);
	
	boolean removeItem(List<ShoppingList> spLists);

}
