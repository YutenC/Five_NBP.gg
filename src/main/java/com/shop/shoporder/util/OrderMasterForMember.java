package com.shop.shoporder.util;

import java.util.List;

import com.shop.shoporder.entity.OrderMaster;

import lombok.Data;

@Data
public class OrderMasterForMember {
	
	private OrderMaster orderMaster;
	
	private List<TransOrderProduct> transOrderProduct;

}
