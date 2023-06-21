package com.shop.product.service;

import com.core.service.CoreService;
import com.shop.shoporder.util.TransOrderProduct;

public interface ProductService extends CoreService{

	TransOrderProduct getOneProduct(Integer productId);
}
