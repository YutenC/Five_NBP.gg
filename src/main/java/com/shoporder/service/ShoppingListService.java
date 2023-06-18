package com.shoporder.service;

import java.util.List;

import com.core.service.CoreService;
import com.shoporder.util.TransOrderProduct;

public interface ShoppingListService extends CoreService{
	
	List<TransOrderProduct> getAllShoppingList(Integer memberId);

}
