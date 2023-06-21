package com.shop.shoporder.service.impl;

import java.util.List;

import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.entity.Member;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.impl.ProductDaoImpl;
import com.shop.product.entity.Product;
import com.shop.shoporder.dao.OrderDetailDao;
import com.shop.shoporder.dao.OrderMasterDao;
import com.shop.shoporder.dao.impl.OrderDetailDaoImple;
import com.shop.shoporder.dao.impl.OrderMasterDaoImpl;
import com.shop.shoporder.entity.OrderDetail;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.entity.PKOrderDeatail;
import com.shop.shoporder.service.OrderMasterService;
import com.shop.shoporder.util.TransOrderProduct;

public class OrderMasterServiceImpl implements OrderMasterService{
	
	private OrderMasterDao omdao;
	private OrderDetailDao odDao;
	private ProductDao pdDao;
	private MemberDao mbDao;
	
	public OrderMasterServiceImpl() {
		this.omdao = new OrderMasterDaoImpl();
		this.odDao = new OrderDetailDaoImple();
		this.pdDao = new ProductDaoImpl();
		this.mbDao = new MemberDaoImpl();
	}

	@Override
	public boolean newOrder(OrderMaster om, List<TransOrderProduct> trObjList, Member member) {
		try {
			beginTransaction();
			omdao.insert(om);
			Integer ordeId = om.getOrderId();
			
			Integer prodcutTotalPrice = 0;
			for (TransOrderProduct trObj : trObjList) {
				PKOrderDeatail pkod = new PKOrderDeatail();
				pkod.setOrderId(ordeId);
				pkod.setProductID(trObj.getProductId());
				
				OrderDetail od = new OrderDetail();
				od.setPkOrderDeatail(pkod);
				od.setQuantity(trObj.getBuyAmount());
				Product checkProduct = pdDao.selectById(trObj.getProductId());
				od.setTotalPrice(trObj.getBuyAmount() * checkProduct.getPrice());
				
				prodcutTotalPrice += trObj.getBuyAmount() * checkProduct.getPrice();
				
				odDao.insert(od);
			}
			
			double newBonus= member.getBonus() - om.getBonusUse() + om.getTotalPrice() * 0.05; // 紅利點數取得公式?
			member.setBonus(newBonus);
			mbDao.update(member);
			
			commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	

}
