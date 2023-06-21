package com.coupon.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.coupon.entity.Coupon;

public interface CouponDao extends CoreDao<Coupon, Integer>{

	Coupon selectByDiscountCode(String code);
	
	List<Coupon> selectByDiscountRange(Integer lower, Integer upper);
	
	List<Coupon> selectByDeadline(java.sql.Date lower, java.sql.Date upper);
	
	List<Coupon> selectByConditionRange(Integer lower, Integer upper);
}
