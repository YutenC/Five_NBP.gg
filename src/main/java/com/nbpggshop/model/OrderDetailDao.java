package com.nbpggshop.model;

import java.util.List;

import com.core.dao.CoreDao;

public interface OrderDetailDao extends CoreDao<OrderDetail, Integer> {

	OrderDetail selectByCompositePK(Integer productId, Integer orderId);
	
	List<OrderDetail> selectByOrderId(Integer orderId);
	
	OrderDetail selectByManagersId(Integer managerId); // 找負責退貨審核的管理員
}
