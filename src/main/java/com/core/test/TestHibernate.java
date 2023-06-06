package com.core.test;

import com.nbpggshop.model.Product;
import com.nbpggshop.model.ProductDaoImpl;

public class TestHibernate {
	
	public static void main (String[] args) {
		ProductDaoImpl pdl = new ProductDaoImpl();
//		
//		Product pd = new Product();
//		
//		pd.setProductName("新商品2");
//		pd.setType("22");
//		pd.setPrice(2000);
//		pd.setAmount(999);
//		pd.setBuyTimes(0);
//		pd.setBrand("PS");
//		pd.setRate(0);
//		pd.setRevieweCount(0);
//		pd.setContent("我是新商品喔2");
//		pd.setLaunchTime(java.sql.Date.valueOf("2023-06-05"));
//		
//		System.out.println(pdl.selectById(1));
		
		for (Product pduni : pdl.selectByName("新商品")) {
			System.out.println(pduni);
		}
		
	}
}
