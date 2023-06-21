package com.product.service;

import com.core.service.CoreService;
import com.shoporder.util.TransOrderProduct;

public interface ProductService extends CoreService{

	TransOrderProduct getOneProduct(Integer productId);
}
