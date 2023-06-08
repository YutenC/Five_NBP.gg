package com.shoporder.model.dao;

import com.core.dao.CoreDao;
import com.shoporder.model.entity.ShoppingList;

public interface ShoppingListDao extends CoreDao<ShoppingList, Integer>{

	ShoppingList selectByCompositePk(Integer memberId, Integer productId);
}
