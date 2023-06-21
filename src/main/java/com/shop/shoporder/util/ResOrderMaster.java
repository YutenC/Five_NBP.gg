package com.shop.shoporder.util;

import java.util.List;

import com.shop.shopproduct.entity.Coupon;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class ResOrderMaster {

	private List<TransOrderProduct> odProducts;
	
	private Coupon checkCoupon;
	
	private Integer usedBonus;
	
	private Double nowBonus;
	
	private String address;
}
