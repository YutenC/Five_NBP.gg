package com.shoporder.service;

import java.util.List;

import com.core.service.CoreService;
import com.shoporder.entity.ShoppingList;
import com.shoporder.util.TransOrderProduct;

public interface ShoppingListService extends CoreService{
	
	List<TransOrderProduct> getAllShoppingList(Integer memberId);
	
	boolean addOneShoppingList(TransOrderProduct trpd, Integer memberId);
	
	boolean removeItem(List<ShoppingList> spLists);

}
