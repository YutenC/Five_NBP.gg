package com.shop.product.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shop.product.entity.Product;

public interface ProductDao extends CoreDao<Product, Integer>{
	
	List<Product> selectByProductName(String name);
	
	List<Product> selectByProductType(String type);
	
	List<Product> selectByProductPriceRange(Integer lower, Integer upper);
	
	List<Product> selectByProductAmount(Integer lower, Integer upper);
	
	List<Product> selectByProductByTimes(Integer lower, Integer upper);

	List<Product> selectByProductByBrand(String brand);

	List<Product> selectByProductByRate(Integer lower, Integer upper);
	
	List<Product> selectByProductByRevieweCount(Integer lower, Integer upper);

	List<Product> selectByProductByLaunchTime(java.sql.Date lower, java.sql.Date upper);
	
	List<Product> selectByProductBuyTimes(Integer limit, Integer offset);
}
