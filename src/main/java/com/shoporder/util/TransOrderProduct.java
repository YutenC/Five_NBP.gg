package com.shoporder.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransOrderProduct {
	
	private Integer productId;
	
	private String productImgUrl;
	
	private String productName;
	
	private String brand;
	
	private Integer buyAmount;
	
	private Integer price;
	
	private Integer stockAmount;
}
