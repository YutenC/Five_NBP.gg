package com.shop.product.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shop.product.entity.ProductImage;

public interface ProductImageDao extends CoreDao<ProductImage, Integer>{
	
	List<ProductImage> selectByProductId(Integer id);
}
