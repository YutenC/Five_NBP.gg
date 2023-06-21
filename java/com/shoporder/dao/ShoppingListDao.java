package com.shoporder.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shoporder.entity.PKShoppingList;
import com.shoporder.entity.ShoppingList;

public interface ShoppingListDao extends CoreDao<ShoppingList, Integer>{

	ShoppingList selectByCompositePk(PKShoppingList pkshlist);
	
	List<ShoppingList> selectByMemberId(Integer memberId);
}
