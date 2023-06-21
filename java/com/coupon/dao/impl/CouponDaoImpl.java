package com.coupon.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.coupon.dao.CouponDao;
import com.coupon.entity.Coupon;

public class CouponDaoImpl implements CouponDao{
	
	@Override
	public int insert(Coupon coupon) {
		getSession().persist(coupon);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		getSession().remove(getSession().get(Coupon.class, id));
		return 1;
	}

	@Override
	public int update(Coupon coupon) {
		getSession().update(coupon);
		return 1;
	}

	@Override
	public Coupon selectById(Integer id) {
		return getSession().get(Coupon.class, id);
	}

	@Override
	public List<Coupon> selectAll() {
		return getSession().createQuery("FROM Coupon ORDER BY deadline", Coupon.class).getResultList();
	}

	@Override
	public Coupon selectByDiscountCode(String code) {
		String hql = "FROM Coupon WHERE discountCode = :discountCode";
		return getSession().createQuery(hql, Coupon.class).setParameter("discountCode", code).getSingleResult();
	}

	@Override
	public List<Coupon> selectByDiscountRange(Integer lower, Integer upper) {
		String hql = "FROM Coupon WHRER discount BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Coupon.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Coupon> selectByDeadline(Date lower, Date upper) {
		String hql = "FROM Coupon WHERE deadline BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Coupon.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

	@Override
	public List<Coupon> selectByConditionRange(Integer lower, Integer upper) {
		String hql = "FROM Coupon WHERE conditionPrice BETWEEN :lower AND :upper";
		return getSession().createQuery(hql, Coupon.class).setParameter("lower", lower).setParameter("upper", upper).getResultList();
	}

}
