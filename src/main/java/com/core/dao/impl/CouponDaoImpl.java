package com.core.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coupon.dao.CouponDao;
import com.coupon.entity.Coupon;

public class CouponDaoImpl implements CouponDao{

	@Override
	public int insert(Coupon coupon) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(coupon);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		}
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		try {
			Coupon coupon = session.get(Coupon.class, id);
			session.remove(coupon);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		}
	}

	@Override
	public int update(Coupon coupon) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(coupon);
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			return 0;
		}
	}

	@Override
	public Coupon selectById(Integer id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			Coupon coupon = session.get(Coupon.class, id);
			tx.commit();
			return coupon;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
	}

	@Override
	public List<Coupon> selectAll() {
		
		return null;
	}

	@Override
	public Coupon selectByDiscountCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> selectByDiscountRange(Integer lower, Integer upper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> selectByDeadline(Date lower, Date upper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> selectByConditionRange(Integer lower, Integer upper) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
