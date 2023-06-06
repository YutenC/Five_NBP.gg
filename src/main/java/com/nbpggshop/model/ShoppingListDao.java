package com.nbpggshop.model;

import com.core.dao.CoreDao;

public interface ShoppingListDao extends CoreDao<ShoppingList, Integer>{

	ShoppingList selectByCompositePk(Integer memberId, Integer productId);
}
