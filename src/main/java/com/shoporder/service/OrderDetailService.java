package com.shoporder.service;

import java.util.Collection;
import java.util.List;

import com.core.service.CoreService;
import com.shoporder.util.ResOrderDetail;
import com.shoporder.util.TransOrderProduct;

public interface OrderDetailService extends CoreService{
	
	List<TransOrderProduct> getOrderDetailByOrderId(Integer orderId);
	
	Collection<ResOrderDetail> getMemberAllOrderDetail(Integer memberId);
}
