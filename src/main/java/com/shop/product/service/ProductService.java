package com.shop.product.service;

import java.util.List;

import com.core.service.CoreService;
import com.shop.shoporder.util.TransOrderProduct;

public interface ProductService extends CoreService{

	TransOrderProduct getOneProduct(Integer productId);
	
	List<TransOrderProduct> getRecomendFromAll(Integer recomendAmount);
}
