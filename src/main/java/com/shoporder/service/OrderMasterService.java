package com.shoporder.service;

import java.util.List;

import com.core.service.CoreService;
import com.member.entity.Member;
import com.shoporder.entity.OrderMaster;
import com.shoporder.util.TransOrderProduct;

public interface OrderMasterService extends CoreService{
	
	boolean newOrder(OrderMaster om, List<TransOrderProduct> trObjList, Member member);

}
