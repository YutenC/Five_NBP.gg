package com.shop.shoporder.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.shop.shoporder.util.OrderMasterServiceConstant;
import com.shop.shoporder.util.TransOrderProduct;
import com.shop.shoporder.util.ViewOrderMaster;

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

	@Override
	public List<ViewOrderMaster> showMgOrderList(Integer limit, Integer offset) {
		try {
			beginTransaction();
			List<ViewOrderMaster> mgOrderList = new ArrayList<>();
			
			List<OrderMaster> omList = omdao.selectAllWithLimitAndOffset(limit, offset);
			for (OrderMaster om : omList) {
				ViewOrderMaster mgOd = new ViewOrderMaster();
				mgOd.setChecked(false);
				Format format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				mgOd.setBuyDate(format.format(om.getCommitDate()));
				mgOd.setMemberId(om.getMemberId());
				mgOd.setOrderId(om.getOrderId());
				mgOd.setTotal(om.getTotalPrice());
				
				int judgeOrder = om.getOrderStatus();
				switch (judgeOrder) {
				case 1:
					mgOd.setOrderStatus("已成立");
					break;
				case 2:
					mgOd.setOrderStatus("已取消");
					break;
				case 3:
					mgOd.setOrderStatus("申請取消");
					break;
				case 4:
					mgOd.setOrderStatus("申請退貨");
					break;	
				}
				
				int judgeDeli = om.getDeliverState();
				switch (judgeDeli) {
				case 0:
					mgOd.setDeliStatus("未出貨");;
					break;
				case 1:
					mgOd.setDeliStatus("已出貨");
					break;
				case 2:
					mgOd.setDeliStatus("已到貨");
				}
				
				int judgePay = om.getPayStatus();
				switch (judgePay) {
				case 1:
					mgOd.setPayStatus("待付款");
					break;
				case 2:
					mgOd.setPayStatus("已付款");
					break;
				case 3:
					mgOd.setPayStatus("貨到付款");
					break;
				}
				
				Member member = mbDao.selectById(om.getMemberId());
				mgOd.setBuyer(member.getNick());
								mgOrderList.add(mgOd);
			}
			
			commit();
			return mgOrderList;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}

	@Override
	public long countDataNum(String character, Integer matchId) {
		try {
			beginTransaction();
			long resultNum = 0;
			if ("member".equals(character)) {
				resultNum = omdao.countdataNum(character, matchId);
			} else if ("manager".equals(character)) {
				resultNum = omdao.countdataNum(null, matchId);
			}
			commit();
			return resultNum;
		} catch (Exception e) {
			rollback();;
			return 0;
		}
	}

	@Override
	public OrderMaster getOne(Integer orderId) {
		try {
			beginTransaction();
			OrderMaster om = omdao.selectById(orderId);
			commit();
			return om;
		} catch (Exception e) {
			rollback();;
			return null;
		}
	}

	@Override
	public boolean updateOrderMaster(OrderMaster fromFn) {
		try {
			beginTransaction();
			omdao.update(fromFn);
			commit();
			return true;
		} catch (Exception e) {
			rollback();;
			return false;
		}
	}
	
	
	
}
