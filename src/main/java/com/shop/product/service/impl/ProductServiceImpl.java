package com.shop.product.service.impl;

import com.shop.product.dao.ProductDao;
import com.shop.product.dao.impl.ProductDaoImpl;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.shoporder.util.TransOrderProduct;

public class ProductServiceImpl implements ProductService{

	private ProductDao pdao;
	
	public ProductServiceImpl() {
		this.pdao = new ProductDaoImpl();
	}

	@Override
	public TransOrderProduct getOneProduct(Integer productId) {
		beginTransaction();
		try {
		Product pd = pdao.selectById(productId);
		if (pd == null) {
			return null;
		}
		TransOrderProduct trpd = new TransOrderProduct();
		trpd.setBrand(pd.getBrand());
		trpd.setBuyAmount(1);
		trpd.setPrice(pd.getPrice());
		trpd.setProductId(pd.getProductId());
		
		if (pd.getPoImages().isEmpty()) {
			trpd.setProductImgUrl(null);
		} else {
			trpd.setProductImgUrl(pd.getPoImages().get(0).getImage());
		}
		trpd.setProductName(pd.getProductName());
		trpd.setStockAmount(pd.getAmount());
		commit();
		return trpd;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}
	
	
	
	
}
