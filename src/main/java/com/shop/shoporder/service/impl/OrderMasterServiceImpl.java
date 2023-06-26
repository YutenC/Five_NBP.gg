package com.shop.shoporder.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.entity.Member;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.impl.ProductDaoImpl;
import com.shop.product.entity.Product;
import com.shop.shoporder.dao.JedisOrderMasterDao;
import com.shop.shoporder.dao.OrderDetailDao;
import com.shop.shoporder.dao.OrderMasterDao;
import com.shop.shoporder.dao.impl.JedisOrderMasterDaoImpl;
import com.shop.shoporder.dao.impl.OrderDetailDaoImple;
import com.shop.shoporder.dao.impl.OrderMasterDaoImpl;
import com.shop.shoporder.entity.OrderDetail;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.entity.PKOrderDeatail;
import com.shop.shoporder.service.OrderMasterService;
import com.shop.shoporder.util.ManageOrder;
import com.shop.shoporder.util.MemberViewOrder;
import com.shop.shoporder.util.OrderSelection;
import com.shop.shoporder.util.TransOrderProduct;

public class OrderMasterServiceImpl implements OrderMasterService{
	
	private OrderMasterDao omdao;
	private OrderDetailDao odDao;
	private ProductDao pdDao;
	private MemberDao mbDao;
	private com.shop.coupon.dao.CouponDao cpDao;
	private JedisOrderMasterDao jdOmDao;
	private Gson gson;
	
	public OrderMasterServiceImpl() {
		this.omdao = new OrderMasterDaoImpl();
		this.odDao = new OrderDetailDaoImple();
		this.pdDao = new ProductDaoImpl();
		this.mbDao = new MemberDaoImpl();
		this.cpDao = new com.shop.coupon.dao.impl.CouponDaoImpl();
		this.jdOmDao = new JedisOrderMasterDaoImpl();
		this.gson = new Gson();
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

	// 配合其他service方法使用，將OrderMaster list 轉為 ManageOrder list
	@Override
	public List<ManageOrder> fromOrderToManageOrder(List<OrderMaster> omlist) {
		List<ManageOrder> mgOrderList = new ArrayList<>();
		
		for (OrderMaster om : omlist) {
			ManageOrder mgOd = new ManageOrder();
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
		return mgOrderList;
	}

	@Override
	public List<ManageOrder> showAllMgOrderList(Integer limit, Integer offset) {
		try {
			beginTransaction();
			List<OrderMaster> omList = omdao.selectAllWithLimitAndOffset(limit, offset);
			List<ManageOrder> mgOrderList = fromOrderToManageOrder(omList);
			commit();
			return mgOrderList;
		} catch (Exception e) {
			rollback();
			return null;
		}
	}
	
	@Override
	public List<ManageOrder> showMgOrderListSortedWithLimitOffset
	(Map<String, String> orderByCondition, Map<String, Integer> limitAndOffset) {
		try {
			beginTransaction();
			List<OrderMaster> omList = omdao.selectOrderbyConditionAndLimitOffset(orderByCondition, limitAndOffset);
			List<ManageOrder> mgOrderList = fromOrderToManageOrder(omList);
			commit();
			return mgOrderList;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	
	@Override
	public List<ManageOrder> ambiguMemberNameSearch
	(String partMemberName, Integer sortWay, Map<String, Integer> limitOffset) {
		try {
			beginTransaction();
			List<Member> candidate = omdao.selectLikeMemberName(partMemberName);
			List<OrderMaster> results = new ArrayList<>();
			for (Member member : candidate) {
				results.addAll(omdao.selectByMemberId(member.getMember_id()));
			}
			
			Integer resultsLength = results.size();
			Integer limit = limitOffset.get("limit");
			Integer offset = limitOffset.get("offset");
			Integer lastIndex = (offset + limit > resultsLength)? resultsLength : offset + limit;
			
			List<OrderMaster> subResults = results.subList(offset, lastIndex);
			if (sortWay == 1) {
				Collections.reverse(subResults);
			}
			
			List<ManageOrder> mgOrderList = fromOrderToManageOrder(subResults);
			commit();
			return mgOrderList;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	@Override
	public Integer ambiguMemberNameSearchLength(String partMemberName) {
		try {
			beginTransaction();
			List<Member> candidate = omdao.selectLikeMemberName(partMemberName);
			List<OrderMaster> results = new ArrayList<>();
			for (Member member : candidate) {
				results.addAll(omdao.selectByMemberId(member.getMember_id()));
			}
			
			Integer resultsLength = results.size();
			commit();
			return resultsLength;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	@Override
	public long countDataNum(Map<String, Integer> condition) {
		try {
			beginTransaction();
			long resultNum = 0;
			if (condition.isEmpty()) {
				resultNum = omdao.simpleCountDatNum();
			} else {
				resultNum = omdao.countdataNumWithCondition(condition);
			}
			commit();
			return resultNum;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
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
	public boolean updateFromManager(OrderMaster fromManager) {
		try {
			beginTransaction();
			omdao.update(fromManager);
			commit();
			return true;
		} catch (Exception e) {
			rollback();;
			return false;
		}
	}

	@Override
	public boolean updateFromMember(OrderMaster fromMember) {
		try {
			beginTransaction();
			OrderMaster oldone = omdao.selectById(fromMember.getOrderId());
			oldone.setOrderStatus(fromMember.getOrderStatus());
			commit();
			return true;
		} catch (Exception e) {
			rollback();;
			return false;
		}
	}

	@Override
	public List<MemberViewOrder> memberOrderList(Map<String, Integer> whereCondition ,Map<String, Integer> limitAndOffset) {
		try {
			beginTransaction();
			List<MemberViewOrder> mvList = new ArrayList<>();
			
			List<OrderMaster> omlist = omdao.selectWithConditionAndLimitOffset(whereCondition, limitAndOffset);
			for (OrderMaster om : omlist) {
				MemberViewOrder mvod = new MemberViewOrder();
				om.setDeliverNumber("");
				om.setBonusUse(0);
				mvod.setOrderMaster(om);
				if (om.getCouponId() != null) {
					mvod.setCoupon(cpDao.selectById(om.getCouponId()));
				}
				
				List<TransOrderProduct> trProdcuts = new ArrayList<>();
				List<OrderDetail> orderDetails = odDao.selectByOrderId(om.getOrderId());
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
					
					trProdcuts.add(trPd);
				}
				mvod.setTrList(trProdcuts);				
				mvList.add(mvod);
			}
			commit();
			return mvList;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();;
			return null;
		}
	}

	@Override
	public List<ManageOrder> getJedisOrderMasterResults(OrderSelection selectionCode, Integer sortWay, Integer offset) {
		try {
			beginTransaction();
			String checkTime = jdOmDao.getResults("Order:SaveTime");
			String retrieve = jdOmDao.getResults("Order:" + selectionCode.getCode());

			if (checkTime == null || retrieve == null) {
				for (int i = 1; i < OrderSelection.values().length; i++ ) {
					saveOrderMasterResults(OrderSelection.values()[i]);
				}
				checkTime = jdOmDao.getResults("Order:SaveTime");
			}
			
			Long saveTimeValue = Long.valueOf(checkTime);
			Long nowTime = new Date().getTime();
			
			if (nowTime - saveTimeValue > 30 * 60 * 1000) {
				for (int i = 1; i < OrderSelection.values().length; i++ ) {
					saveOrderMasterResults(OrderSelection.values()[i]);
				}
				retrieve = jdOmDao.getResults("Order:" + selectionCode.getCode());
			} 

			List<OrderMaster> jedisContent = gson.fromJson(retrieve, new TypeToken<List<OrderMaster>>(){});
			
			if (sortWay == 1) {
				Collections.reverse(jedisContent);
			}
			
			int limit = 10;
			int jedisContentSize = jedisContent.size();
			
			List<OrderMaster> omList = jedisContent.subList(offset, (offset + limit > jedisContentSize)? jedisContentSize : offset + limit);
			List<ManageOrder> mgOrderList = fromOrderToManageOrder(omList);
			commit();
			return mgOrderList;			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	@Override  //須搭配其他服務使用
	public boolean saveOrderMasterResults(OrderSelection selectCode) {
		Long now = new Date().getTime();
		jdOmDao.saveOrderMasterResults("Order:SaveTime", now.toString());
		
		List<OrderMaster> newResults = new ArrayList<>();
		
		switch (selectCode) {
		case PAID:
			newResults = omdao.selectByPaystatus(2);
			break;
		case UNPAID:
			newResults = omdao.selectByPaystatus(1);
			break;
		case PAIDONEDELI:
			newResults = omdao.selectByPaystatus(3);
			break;
		case DELIVERD:
			newResults = omdao.selectByDeliverState(1);
			break;
		case UNDELI:
			newResults = omdao.selectByDeliverState(0);
			break;
		case DONE:
			newResults = omdao.selectByOrderStatus(1);
			break;
		case CANCELED:
			newResults = omdao.selectByOrderStatus(2);
			break;
		case APPLYCAN:
			newResults = omdao.selectByOrderStatus(3);
			break;
		case APPLYRETURN:
			newResults = omdao.selectByOrderStatus(4);
			break;
		}
		
		String key = "Order:" + selectCode.getCode();
		String content = gson.toJson(newResults);
		jdOmDao.saveOrderMasterResults(key, content);
		return true;
	}

	@Override
	public boolean renewOrderMasterResults() {
		try {
			beginTransaction();
			for (int i = 1; i < OrderSelection.values().length; i++ ) {
				saveOrderMasterResults(OrderSelection.values()[i]);
			}
			commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return false;
		}
	}

}
