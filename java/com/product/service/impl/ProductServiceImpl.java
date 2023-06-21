package com.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.product.dao.ProductDao;
import com.product.dao.impl.ProductDaoImpl;
import com.product.entity.Product;
import com.product.service.ProductService;
import com.shoporder.util.TransOrderProduct;

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

	@Override
	public List<TransOrderProduct> getRecomendFromAll(Integer recomendAmount) {
		try {
			beginTransaction();
			List<TransOrderProduct> trPdList = new ArrayList<>();
			
			List<Product> pdList = pdao.selectByProductBuyTimes(recomendAmount, 0);
			for (Product pd : pdList) {
				TransOrderProduct trPd = new TransOrderProduct();
				trPd.setPrice(pd.getPrice());
				trPd.setProductId(pd.getProductId());
				if (!pd.getPoImages().isEmpty()) {
					trPd.setProductImgUrl(pd.getPoImages().get(0).getImage());
				}
				trPd.setProductName(pd.getProductName());
				
				trPdList.add(trPd);
			}
	
			commit();
			return trPdList;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}
	
	
	
	
}
