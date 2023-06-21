package com.shoporder.service.impl;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.product.dao.ProductDao;
import com.product.dao.impl.ProductDaoImpl;
import com.product.entity.Product;
import com.shoporder.dao.OrderDetailDao;
import com.shoporder.dao.OrderMasterDao;
import com.shoporder.dao.impl.OrderDetailDaoImple;
import com.shoporder.dao.impl.OrderMasterDaoImpl;
import com.shoporder.entity.OrderDetail;
import com.shoporder.entity.OrderMaster;
import com.shoporder.service.OrderDetailService;
import com.shoporder.util.ResOrderDetail;
import com.shoporder.util.TransOrderProduct;

public class OrderDetailServiceImpl implements OrderDetailService{

	private OrderDetailDao odDao;
	private OrderMasterDao omDao;
	private ProductDao pdDao;
	
	public OrderDetailServiceImpl() {
		this.odDao = new OrderDetailDaoImple();
		this.omDao = new OrderMasterDaoImpl();
		this.pdDao = new ProductDaoImpl();
	}

	@Override
	public List<TransOrderProduct> getOrderDetailByOrderId(Integer orderId) {
		try {
			beginTransaction();
			List<TransOrderProduct> odProdcuts = new ArrayList<>();
			List<OrderDetail> orderDetails = odDao.selectByOrderId(orderId);
			for (OrderDetail od : orderDetails) {
				Product pd = pdDao.selectById(od.getPkOrderDeatail().getProductID());
				TransOrderProduct trPd = new TransOrderProduct();
				
				trPd.setBrand(pd.getBrand());
				trPd.setBuyAmount(od.getQuantity());
				trPd.setChecked(true);
				trPd.setPrice(pd.getPrice());
				trPd.setProductId(od.getPkOrderDeatail().getProductID());
				trPd.setProductName(pd.getProductName());
				if (pd.getPoImages().isEmpty()) {
					trPd.setProductImgUrl(null);
				} else {
					trPd.setProductImgUrl(pd.getPoImages().get(0).getImage());
				}
				trPd.setStockAmount(pd.getAmount());
				
				odProdcuts.add(trPd);
			}
			
			commit();
			return odProdcuts;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public Collection<ResOrderDetail> getMemberAllOrderDetail(Integer memberId) {
		try {
			beginTransaction();
			List<OrderMaster> omList = omDao.selectByMemberId(memberId);
			List<ResOrderDetail> resODList = new ArrayList<>();
			Map<Integer, ResOrderDetail> checkMap = new HashMap<>();
			
			for (OrderMaster om : omList) {
				List<OrderDetail> odList = odDao.selectByOrderId(om.getOrderId());
				for (OrderDetail od : odList) {
					ResOrderDetail rsOD = new ResOrderDetail();
					rsOD.setProductAmount(od.getQuantity());
					rsOD.setProductId(od.getPkOrderDeatail().getProductID());
					rsOD.setPurchaseDate(om.getCommitDate());
					Product pd = pdDao.selectById(od.getPkOrderDeatail().getProductID());
					rsOD.setProductName(pd.getProductName());
					rsOD.setProductPrice(pd.getPrice());
					ResOrderDetail checkUnit = (ResOrderDetail)checkMap.get(pd.getProductId());
					if (checkUnit == null ||
							(checkUnit.getPurchaseDate().getTime() < rsOD.getPurchaseDate().getTime())) { 
						checkMap.put(pd.getProductId(), rsOD);
					} 
				}
			}
			commit();
			return checkMap.values();
		} catch (Exception e) {
			rollback();
			return null;
		}
	}
	
	

}
