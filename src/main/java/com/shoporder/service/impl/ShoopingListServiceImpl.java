package com.shoporder.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.product.dao.ProductDao;
import com.product.dao.ProductImageDao;
import com.product.dao.impl.ProductDaoImpl;
import com.product.dao.impl.ProductImageDaoImpl;
import com.product.entity.Product;
import com.product.entity.ProductImage;
import com.shoporder.dao.JedicShoppingListDao;
import com.shoporder.dao.impl.JedisShoppingListDaoImpl;
import com.shoporder.entity.ShoppingList;
import com.shoporder.service.ShoppingListService;
import com.shoporder.util.TransOrderProduct;

public class ShoopingListServiceImpl implements ShoppingListService{

	private JedicShoppingListDao jediShdao = new JedisShoppingListDaoImpl();
	private ProductDao pdao = new ProductDaoImpl();
	private ProductImageDao pdimgdao = new ProductImageDaoImpl();
	
	@Override
	public List<TransOrderProduct> getAllShoppingList(Integer memberId) {
		List<TransOrderProduct> result = new ArrayList<>();
		try {
			beginTransaction();
			List<ShoppingList> listOfsplist = jediShdao.selectByMemberId(memberId);
			for (ShoppingList splist : listOfsplist) {
				Product pd = pdao.selectById(splist.getPkShoppingList().getProductId());
				if (pd == null) {
					continue;
				}
				List<ProductImage> pdimgs = pdimgdao.selectByProductId(pd.getProductId());
				TransOrderProduct trsplist = new TransOrderProduct();
				trsplist.setProductId(pd.getProductId());
				if (pdimgs.isEmpty()) {
					trsplist.setProductImgUrl(null);
				} else {
					trsplist.setProductImgUrl(pdimgs.get(0).getImage());
				}
				trsplist.setProductName(pd.getProductName());
				trsplist.setBrand(pd.getBrand());
				trsplist.setBuyAmount(splist.getQuantity());
				trsplist.setPrice(pd.getPrice());
				trsplist.setStockAmount(pd.getAmount());
				result.add(trsplist);
			}
			commit();
			return result;
		} catch (Exception e) {
			rollback();
			return null;
		}
		
	}
	
	

}
