package com.shop.shoporder.service;

import java.util.List;

import com.core.service.CoreService;
import com.member.entity.Member;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.util.TransOrderProduct;
import com.shop.shoporder.util.ViewOrderMaster;

public interface OrderMasterService extends CoreService{
	
	boolean newOrder(OrderMaster om, List<TransOrderProduct> trObjList, Member member);
	
	List<ViewOrderMaster> showMgOrderList(Integer limit, Integer offset);
	
	long countDataNum(String character, Integer matchId);
	
	OrderMaster getOne(Integer orderId);
	
	boolean updateOrderMaster(OrderMaster fromFn);

}
