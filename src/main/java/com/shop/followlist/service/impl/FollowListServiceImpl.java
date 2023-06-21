package com.shop.followlist.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.shop.followlist.dao.FollowListDao;
import com.shop.followlist.dao.impl.FollowListDaoImpl;
import com.shop.followlist.entity.FollowList;
import com.shop.followlist.entity.PKFollowList;
import com.shop.followlist.service.FollowListService;
import com.shop.followlist.util.ResFollowList;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.ProductImageDao;
import com.shop.product.dao.impl.ProductDaoImpl;
import com.shop.product.dao.impl.ProductImageDaoImpl;
import com.shop.product.entity.Product;
import com.shop.product.entity.ProductImage;

public class FollowListServiceImpl implements FollowListService{
	
	private FollowListDao followProductDao;
	private ProductDao productdao;
	private ProductImageDao pdImgDao;
	
	public FollowListServiceImpl() {
		this.followProductDao = new FollowListDaoImpl();
		this.productdao = new ProductDaoImpl();
		this.pdImgDao = new ProductImageDaoImpl();
	}

	@Override
	public List<ResFollowList> getAllFollowProduct(Integer memberId) {
		try {
			beginTransaction();

			List<ResFollowList> rsFlist = new ArrayList<ResFollowList>();
			
			List<FollowList> flist = followProductDao.selectByMemeberId(memberId);
			
			for (int i = 0; i < flist.size(); i++) {
				ResFollowList rf = new ResFollowList();
				Integer pdId = flist.get(i).getPkFollowList().getProductId();
				rf.setProductId(pdId);
				
				Product pd = productdao.selectById(pdId);
				rf.setProductName(pd.getProductName());
				rf.setProductPrice(pd.getPrice());
				rf.setProductAmount(pd.getAmount());
				
				List<ProductImage> pdimgs = pdImgDao.selectByProductId(pdId);
				// 往後設定取用封面圖(可能需新增表格欄位，或是固定存取為該商品照片們的第一張)
				if (!pdimgs.isEmpty()) {
					rf.setProductImgUrl(pdimgs.get(0).getImage());
				}
				rsFlist.add(rf);
			}
			
			commit();
			return rsFlist;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public boolean deleteFollowList(Integer memberId, Integer productId) {
		try {
			beginTransaction();
			PKFollowList pkflist = new PKFollowList(memberId, productId);
			FollowList flist = new FollowList(pkflist);
			followProductDao.deleteByCompositePK(flist);
			commit();
			return true;
		} catch (Exception e) {
			rollback();
			return false;
		}
	}

	
}
