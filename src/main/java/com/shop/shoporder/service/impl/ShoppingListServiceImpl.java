package com.shoporder.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.product.dao.ProductDao;
import com.product.dao.ProductImageDao;
import com.product.dao.impl.ProductDaoImpl;
import com.product.dao.impl.ProductImageDaoImpl;
import com.product.entity.Product;
import com.product.entity.ProductImage;
import com.shoporder.dao.JedisShoppingListDao;
import com.shoporder.dao.impl.JedisShoppingListDaoImpl;
import com.shoporder.entity.PKShoppingList;
import com.shoporder.entity.ShoppingList;
import com.shoporder.service.ShoppingListService;
import com.shoporder.util.TransOrderProduct;

public class ShoppingListServiceImpl implements ShoppingListService{

	private JedisShoppingListDao jediShdao;
	private ProductDao pdao;
	private ProductImageDao pdimgdao;
	
	public ShoppingListServiceImpl() {
		this.jediShdao = new JedisShoppingListDaoImpl();
		this.pdao = new ProductDaoImpl();
		this.pdimgdao = new ProductImageDaoImpl();
	}
	
	@Override
	public List<TransOrderProduct> getAllShoppingList(Integer memberId) {
		try {
			beginTransaction();
			List<TransOrderProduct> result = new ArrayList<>();
			List<ShoppingList> listOfsplist = jediShdao.selectByMemberId(memberId);
			for (ShoppingList splist : listOfsplist) {
				Product pd = pdao.selectById(splist.getPkShoppingList().getProductId());
				if (pd == null) {
					continue;
				}
				List<ProductImage> pdimgs = pdimgdao.selectByProductId(pd.getProductId());
				TransOrderProduct trspd = new TransOrderProduct();
				trspd.setProductId(pd.getProductId());
				if (pdimgs.isEmpty()) {
					trspd.setProductImgUrl(null);
				} else {
					trspd.setProductImgUrl(pdimgs.get(0).getImage());
				}
				trspd.setProductName(pd.getProductName());
				trspd.setBrand(pd.getBrand());
				trspd.setBuyAmount(splist.getQuantity());
				trspd.setPrice(pd.getPrice());
				trspd.setStockAmount(pd.getAmount());
				trspd.setChecked(true);
				result.add(trspd);
			}
			commit();;
			return result;
		} catch (Exception e) {
			rollback();
			return null;
		}
		
	}

	@Override
	public boolean addOneShoppingList(TransOrderProduct trpd, Integer memberId) {
		try {
			beginTransaction();
			ShoppingList slist = new ShoppingList();
			PKShoppingList pkslist = new PKShoppingList();
			pkslist.setMemmberId(memberId);
			pkslist.setProductId(trpd.getProductId());
			slist.setPkShoppingList(pkslist);
			slist.setQuantity(trpd.getBuyAmount());
			jediShdao.update(slist);
			commit();;
			return true;
		} catch (Exception e) {
			rollback();;
			return false;
		}
	}

	@Override
	public boolean removeItem(List<ShoppingList> spLists) {
		try {
			beginTransaction();
			for (ShoppingList splist : spLists) {
				jediShdao.delete(splist);
			}
			commit();
			return true;
		} catch (Exception e) {
			rollback();;
			return false;
		}
	}
	
	

}
