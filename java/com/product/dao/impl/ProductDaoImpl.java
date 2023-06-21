package com.product.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.product.dao.ProductDao;
import com.product.entity.Product;

public class ProductDaoImpl implements ProductDao{

	@Override
	public int insert(Product product) {
		getSession().persist(product);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		getSession().remove(getSession().get(Product.class, id));
		return 1;
	}

	@Override
	public int update(Product product) {
		getSession().update(product);
		return 1;
	}

	@Override
	public Product selectById(Integer id) {
		return getSession().get(Product.class, id);
	}

	@Override
	public List<Product> selectAll() {
		String hql = "FROM Product ORDER BY rate";
		return getSession().createQuery(hql, Product.class).getResultList();
	}

	@Override
	public List<Product> selectByProductName(String name) {
		String hql = "FROM Product WHERER productName LIKE '%'||:productName||'%'";
		return getSession().createQuery(hql, Product.class).setParameter("productName", name).getResultList();
	}

	@Override
	public List<Product> selectByProductType(String type) {
		String hql = "FROM Product WHERE type = :type";
		return getSession().createQuery(hql, Product.class).setParameter("type", type).getResultList();
	}

	@Override
	public List<Product> selectByProductPriceRange(Integer lower, Integer upper) {
		String hql = "FROM Product WHERE price BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductAmount(Integer lower, Integer upper) {
		String hql = "FROM Product WHERE amount BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductByTimes(Integer lower, Integer upper) {
		String hql = "FROM Product WHERE buyTimes BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductByBrand(String brand) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);
		cq.where(cb.equal(root.get("brand"), brand));
		return getSession().createQuery(cq).getResultList();
	}

	@Override
	public List<Product> selectByProductByRate(Integer lower, Integer upper) {
		String hql = "FROM Product WHERE rate BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductByRevieweCount(Integer lower, Integer upper) {
		String hql = "FROM Product WHERE revieweCount BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductByLaunchTime(Date lower, Date upper) {
		String hql = "FROM Product WHERE launchTime BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Product.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Product> selectByProductBuyTimes(Integer limit, Integer offset) {
		String hql = "FROM Product ORDER BY buyTimes";
		return getSession()
				.createQuery(hql, Product.class)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}
	
}
