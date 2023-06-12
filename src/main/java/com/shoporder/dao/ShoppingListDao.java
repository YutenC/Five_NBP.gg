package com.shoporder.dao;

import com.core.dao.CoreDao;
import com.shoporder.entity.ShoppingList;

public interface ShoppingListDao extends CoreDao<ShoppingList, Integer>{

	ShoppingList selectByCompositePk(Integer memberId, Integer productId);
}
