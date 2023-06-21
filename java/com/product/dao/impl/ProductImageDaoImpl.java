package com.product.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.product.dao.ProductImageDao;
import com.product.entity.ProductImage;

public class ProductImageDaoImpl implements ProductImageDao{

	@Override
	public int insert(ProductImage productImage) {
		getSession().persist(productImage);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		getSession().remove(getSession().get(ProductImage.class, id));
		return 1;
	}

	@Override
	public int update(ProductImage productImage) {
		getSession().update(productImage);
		return 1;
	}

	@Override
	public ProductImage selectById(Integer id) {
		return getSession().get(ProductImage.class, id);
	}

	@Override
	public List<ProductImage> selectAll() {
		return getSession().createQuery("FROM ProductImage ORDER BY productId", ProductImage.class).getResultList();
	}

	@Override
	public List<ProductImage> selectByProductId(Integer id) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<ProductImage> cq = cb.createQuery(ProductImage.class);
		Root<ProductImage> root = cq.from(ProductImage.class);
		cq.where(cb.equal(root.get("productId"), id));
		return getSession().createQuery(cq).getResultList();
	}

}
