package com.shop.shoporder.util;

import java.util.List;

import com.shop.shoporder.entity.OrderMaster;

import lombok.Data;

@Data
public class MemberViewOrder {
	
	private OrderMaster orderMaster;
	
//	private Coupon coupon;
	
	private com.shop.coupon.entity.Coupon coupon;
	
	private List<TransOrderProduct> trList;
}
