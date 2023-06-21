package com.shop.shoporder.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shop.shoporder.entity.OrderMaster;

public interface OrderMasterDao extends CoreDao<OrderMaster, Integer> {

	List<OrderMaster> selectByMemberId(Integer memberId); //找誰買
	
	List<OrderMaster> selectByCommitDate(java.sql.Date lower, java.sql.Date upper); // 找特定日期區間訂單
	
	OrderMaster selectByDeliverNumber(String deliverNumber);
	
	List<OrderMaster> selectByCommitType(Integer commitType); // 找付款方式
	
	List<OrderMaster> selectByDeliverState(Integer deliverState); // 找出貨狀態

	List<OrderMaster> selectByDeliverLocation(String location); // 模糊比對出貨地址
	
	List<OrderMaster> selectByPickType(Integer pickType); // 找取貨方式
	
	OrderMaster selectByCouponId(Integer couponId); // 找哪張單用過特定的優惠券
	
	List<OrderMaster> selectByOrderStatus(Integer orderstatus);
	
	List<OrderMaster> selectByPaystatus(Integer payStatus);
}
