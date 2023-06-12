package com.shoporder.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shoporder.dao.ShoppingListDao;
import com.shoporder.entity.ShoppingList;

public class ShoppingListDaoImpl implements ShoppingListDao {

	@Override
	public int insert(ShoppingList shoppingList) {
		getSession().persist(shoppingList);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		getSession().remove(selectById(id));
		return 1;
	}

	@Override
	public int update(ShoppingList shoppingList) {
		getSession().update(shoppingList);
		return 1;
	}

	@Override
	public ShoppingList selectById(Integer id) {
		return getSession().get(ShoppingList.class, id);
	}

	@Override
	public List<ShoppingList> selectAll() {
		return getSession()
				.createQuery("FROM ShoppingList ORDER BY memberId", ShoppingList.class)
				.getResultList();
	}

	@Override
	public ShoppingList selectByCompositePk(Integer memberId, Integer productId) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<ShoppingList> criteriaQuery = criteriaBuilder.createQuery(ShoppingList.class);
		Root<ShoppingList> root = criteriaQuery.from(ShoppingList.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("memberId"), memberId));
		criteriaQuery.where(criteriaBuilder.equal(root.get("productId"), productId));
		return getSession()
				.createQuery(criteriaQuery)
				.uniqueResult();
	}

}
