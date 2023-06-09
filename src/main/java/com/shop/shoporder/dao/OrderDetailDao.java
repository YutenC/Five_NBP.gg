package com.shop.shoporder.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shop.shoporder.entity.OrderDetail;

public interface OrderDetailDao extends CoreDao<OrderDetail, Integer> {

	OrderDetail selectByCompositePK(Integer productId, Integer orderId);
	
	List<OrderDetail> selectByOrderId(Integer orderId);
	
	OrderDetail selectByManagerId(Integer managerId); // 找負責退貨審核的管理員
}
