package com.shop.shoporder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.member.entity.Member;
import com.member.util.MemberConstants;
import com.shop.product.util.CouponServiceConstant;
import com.shop.product.util.ProductServiceConstant;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.util.ManageOrder;
import com.shop.shoporder.util.MemberViewOrder;
import com.shop.shoporder.util.OrderDetailServiceConstant;
import com.shop.shoporder.util.OrderMasterServiceConstant;
import com.shop.shoporder.util.OrderSelection;
import com.shop.shoporder.util.ResOrderMaster;
import com.shop.shoporder.util.TransOrderProduct;
import com.shop.shopproduct.entity.Coupon;

@WebServlet("/OrderMaster")
public class OrderMasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Gson gson;
 
    
	public OrderMasterController() {
        super();
        this.gson = new Gson();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	Member login = new Member();
		login.setAccount("ReimuHakurei");
		login.setPassword("HakureiShrine");
		
		Member mber = MemberConstants.SERVICE.login(login);
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("member", mber);
		
		if (httpSession.getAttribute("member") == null) {
			res.sendRedirect("/Five_NBP.gg");
			return;
		}
		
		Member member = (Member)httpSession.getAttribute("member");
		Integer memberId = member.getMember_id();
		
    	req.setCharacterEncoding("UTF-8");
		
    	Gson gson = new Gson();
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("application/json");
    	PrintWriter pw = res.getWriter();
    	
    	if (req.getParameter("manageAll") != null) {
    		int limit = 10;
    		Integer offset = Integer.valueOf(req.getParameter("manageAll")) * limit ;
    		
    		Integer sortBy = Integer.valueOf(req.getParameter("sortBy"));
    		
    		Map<String, String> orderBy = new HashMap<>();
    		String sortKey = null;
    		switch (sortBy) {
			case 1:
				orderBy.put("orderBy", "orderId");
				break;
			case 2:
				orderBy.put("orderBy", "memberId");
				break;
			case 3:
				orderBy.put("orderBy", "commitDate");
				break;
			case 4:
				orderBy.put("orderBy", "totalPrice");
				break;
			case 5:
				orderBy.put("orderBy", "orderStatus");
				break;
			case 6:
				orderBy.put("orderBy", "payStatus");
				break;
			case 7:
				orderBy.put("orderBy", "deliverState");
				break;
			}

    		Integer sortWay = Integer.valueOf(req.getParameter("sortWay"));
    		switch (sortWay) {
			case 0:
				orderBy.put("orderWay", "ASC");
				break;
			case 1:
				orderBy.put("orderWay", "DESC");
				break;
			}
    		
    		List<ManageOrder> mgOrderList = null;
    		switch (sortBy) {
			case 0:
				mgOrderList = OrderMasterServiceConstant.OMSERVICE.showAllMgOrderList(limit, offset);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				Map<String, Integer> limitAndOffset = new HashMap<>();
				limitAndOffset.put("limit", 10);
				limitAndOffset.put("offset", offset);
				mgOrderList = OrderMasterServiceConstant.OMSERVICE.showMgOrderListSortedWithLimitOffset(orderBy, limitAndOffset);
				break;
			}
    		pw.println(gson.toJson(mgOrderList));
    		return;
    	}
    	
    	String manageCondition = req.getParameter("manageCondition");
    	if (manageCondition != null) {
    		Integer offset = Integer.valueOf(req.getParameter("offset")) * 10;
    		Integer selection = Integer.valueOf(req.getParameter("selection"));
    		Integer sortWay = Integer.valueOf(req.getParameter("sortWay"));
    		OrderSelection os = OrderSelection.values()[selection - 1];
    		List<ManageOrder> results = OrderMasterServiceConstant.OMSERVICE.getJedisOrderMasterResults(os, sortWay, offset);
    		pw.println(gson.toJson(results));
    		return;
    	}
    	
    	String fresh = req.getParameter("fresh");
    	if (fresh != null) {
    		pw.println(gson.toJson(OrderMasterServiceConstant.OMSERVICE.renewOrderMasterResults()));
    		return;
    	}
    	
    	String character = req.getParameter("countListLength");
    	if (character != null) {
    		Map<String, Integer> condition = new HashMap<>();
    		if ("manager".equals(character)) {
    			String criteria = req.getParameter("criteria");
    			if (criteria != null) {
    				Integer criteriaValue = Integer.valueOf(criteria);
    				OrderSelection os = OrderSelection.values()[criteriaValue - 1];
    				switch (os) {
					case ALL:
						break;
					case PAID:
						condition.put("payStatus", 2);
						break;
					case UNPAID:
						condition.put("payStatus", 1);
						break;
					case PAIDONEDELI:
						condition.put("payStatus", 3);
						break;
					case DELIVERD:
						condition.put("deliverState", 1);
						break;
					case UNDELI:
						condition.put("deliverState", 0);
						break;
					case DONE:
						condition.put("orderStatus", 1);
						break;
					case CANCELED:
						condition.put("orderStatus", 2);
						break;
					case APPLYCAN:
						condition.put("orderStatus", 3);
						break;
					case APPLYRETURN:
						condition.put("orderStatus", 4);
						break;
    				}
    			}
    		} else if ("member".equals(character)) {
    			condition.put("memberId", member.getMember_id());
    		}
    		pw.println(gson.toJson(OrderMasterServiceConstant.OMSERVICE.countDataNum(condition)));
    	}
    	
    	String getOne = req.getParameter("getOne");
    	if (getOne != null) {
    		Integer orderId = Integer.valueOf(getOne);
    		OrderMaster om = OrderMasterServiceConstant.OMSERVICE.getOne(orderId);
    		pw.println(gson.toJson(om));
    		return;
    	}
    	
    	String searchUser = req.getParameter("searchUser");
    	if (searchUser != null) {
//    		System.out.println(searchUser);
//    		System.out.println(req.getParameter("offset"));
//    		System.out.println(req.getParameter("sortWay"));
    		Integer offset = Integer.valueOf(req.getParameter("offset")) * 10;
    		Integer sortWay = Integer.valueOf(req.getParameter("sortWay"));
    		Map<String, Integer> limitOffset = new HashMap<>();
    		limitOffset.put("limit", 10);
    		limitOffset.put("offset", offset);
    		
    		pw.println(gson.toJson(OrderMasterServiceConstant.OMSERVICE.ambiguMemberNameSearch(searchUser, sortWay, limitOffset)));
    		return;
    		
    	}
    	
    	String searchLength = req.getParameter("searchLength");
    	if (searchLength != null) {
    		String keyword = req.getParameter("keyword");
    		pw.println(gson.toJson(OrderMasterServiceConstant.OMSERVICE.ambiguMemberNameSearchLength(keyword)));
    		return;
    	}
    	
    	String memberAll = req.getParameter("memberAll");
    	if (memberAll != null) {
    		String criteria = req.getParameter("criteria");
    		
    		Integer setNum = 0;
    		setNum = Integer.valueOf(memberAll);
    		
    		Integer criteriaNum = 1;
    		if (criteria != null) {
    			criteriaNum = Integer.valueOf(criteria);
    		}
    		
    		Map<String, Integer> limitOffset = new TreeMap<>();
    		limitOffset.put("LIMIT", 10);
    		limitOffset.put("OFFSET", setNum);
    		
    		Map<String, Integer> whereCondition = new HashMap<>();
    		
    		whereCondition.put("memberId", memberId);
    		
    		switch (criteriaNum) {
			case 1:
				break;
			case 2:
				whereCondition.put("payStatus", 1);
				break;
			case 3:
				whereCondition.put("payStatus", 2);
				break;
			case 4:
				whereCondition.put("deliverState", 1);
				break;
			case 5:
				whereCondition.put("deliverState", 0);
				break;
			}

    		List<MemberViewOrder> mvList = OrderMasterServiceConstant.OMSERVICE.memberOrderList(whereCondition, limitOffset);
    		pw.println(gson.toJson(mvList));
    		return;
    	}
    }
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		
		HttpSession httpSession = req.getSession();
		
		Member login = new Member();
		login.setAccount("ReimuHakurei");
		login.setPassword("HakureiShrine");
		Member mber = MemberConstants.SERVICE.login(login);
		httpSession.setAttribute("member", mber);
		
		if (httpSession.getAttribute("member") == null) {
			res.sendRedirect("/Five_NBP.gg");
			return;
		}
		
		Member member = (Member)httpSession.getAttribute("member");
		Integer memberId = member.getMember_id();
		
		String demand = req.getParameter("demand");
		
		if ("checkOut".equals(demand)) {
			// 取得前端回傳購物列表(使用axios params傳參數會遇到[]中括號無法放在query string的異常問題(或許改tomcat設定就可解決?)
			// 因此改放在axios data，用reader讀取request body內部資料
			Reader rd = req.getReader();
			BufferedReader brd = new BufferedReader(rd);
			String reqStr = brd.readLine();
			reqStr = reqStr.substring(1, reqStr.length() - 1);
						
			int cardIndex = reqStr.indexOf("card");
			int addressIndex = reqStr.indexOf("address");
						
			// 取得購物明細傳輸商品類別物件
			String trObjStr = reqStr.substring(0, cardIndex - 2);
			trObjStr = trObjStr.substring(trObjStr.indexOf(":") + 1, trObjStr.length());
			List<TransOrderProduct> trObjList = gson.fromJson(trObjStr, new TypeToken<List<TransOrderProduct>>(){});	// 使用Gson轉為java List<T>
			
			List<TransOrderProduct> purchaseProducts = new ArrayList<>();
			for (TransOrderProduct trOdPd : trObjList) {
				if (trOdPd.isChecked() == true) {
					purchaseProducts.add(trOdPd);
				}
			}
			req.setAttribute("purchaseProducts", purchaseProducts);
//			System.out.println(trObjList);
						
			// 取得結帳信用卡資訊Json物件
			String cardStr = reqStr.substring(cardIndex  , addressIndex - 2);
			cardStr = cardStr.substring(cardStr.indexOf(":") + 1, cardStr.length());
//			System.out.println(cardStr);
			JsonObject cardDetail = JsonParser.parseString(cardStr).getAsJsonObject();
			req.setAttribute("card", cardDetail);
			
			// 取得配送地址資訊Json物件
			String addressStr = reqStr.substring(addressIndex, reqStr.length());
			addressStr = addressStr.substring(addressStr.indexOf(":") + 1, addressStr.length());
//			System.out.println(addressStr);
			JsonObject addressDetail = JsonParser.parseString(addressStr).getAsJsonObject();
			
			// 創建OrderMaster類物件並存入資料庫
			OrderMaster om = new OrderMaster();
			om.setMemberId(memberId);
			
			java.util.Date date = new java.util.Date();
			om.setCommitDate(new java.sql.Timestamp(date.getTime()));
			
			Integer commitType = 0;
			String payment = req.getParameter("payment");
			switch (payment) {
			case "credit":
				commitType = 1;
				om.setPayStatus(2);
				break;
			case "transfer":
				commitType = 2;
				om.setPayStatus(1);
				break;
			case "onDeliver":
				commitType = 3;
				om.setPayStatus(3);
				break;
			}
			om.setCommitType(commitType);
			
			om.setDeliverState(0);
			
			Integer takuhai = 100;
			Integer toCvs = 200;
			String pickType = req.getParameter("deliver");
			om.setDeliverFee(pickType.equals("takuhai")? takuhai : toCvs);
			om.setPickType(pickType.equals("takuhai")? takuhai/100 : toCvs/100);
			
			om.setDeliverLocation(addressDetail.get("county").toString().replace("\"", "") + addressDetail.get("address").toString().replace("\"", ""));
			
			Integer productPrice = 0;
			for (TransOrderProduct trObj : purchaseProducts) {
				if (trObj.isChecked() == true) {
					TransOrderProduct checkProduct = ProductServiceConstant.PDSERVICE.getOneProduct(trObj.getProductId());
					productPrice += checkProduct.getPrice() * trObj.getBuyAmount();
				}
			}
			
			String discountRadio = req.getParameter("discountRadio");
			String couponStr = null;
			String reqBonus = null;
			switch (discountRadio) {
			case "coupon":
				couponStr = req.getParameter("coupon");
				break;
			case "bonus":
				reqBonus = req.getParameter("bonus");
				break;
			}
			
			Coupon checkCoupon = null;
			Integer couponDiscount = 0;
			Integer useBonus = 0;

			if (couponStr != null && couponStr.trim().length() != 0) {
				Coupon reqCoupon = null;
				try {
					reqCoupon = gson.fromJson(couponStr, Coupon.class);
					checkCoupon = CouponServiceConstant.CPSERVICE.getCouponByDiscountCode(reqCoupon.getDiscountCode());
				} catch (Exception e) {
					System.out.println("Cant convert to Coupon.class");
				}
				if (checkCoupon != null) {
					om.setCouponId(checkCoupon.getId());
					Integer conditionPrice = checkCoupon.getConditionPrice();
					couponDiscount = checkCoupon.getDiscount();
					if (productPrice < conditionPrice) {
						couponDiscount = 0;
					}
				}
			}
			
			if (checkCoupon == null && reqBonus != null && reqBonus.trim().length() != 0){
				useBonus = Integer.valueOf(reqBonus.trim());
				if (useBonus > member.getBonus()) {
					useBonus = Integer.valueOf(member.getBonus().toString());
				}
			}
			
			om.setBonusUse(useBonus);

			om.setTotalPrice(productPrice - couponDiscount - useBonus);
			
			om.setOrderStatus(1);
			
			boolean addNewOM = OrderMasterServiceConstant.OMSERVICE.newOrder(om, purchaseProducts, member);

			req.setAttribute("thisOrder", om);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ShoppingList");
			dispatcher.include(req, res);
			
			ResOrderMaster resOM = new ResOrderMaster();
			
			resOM.setOrderId(om.getOrderId());
			
			List<TransOrderProduct> odProducts = OrderDetailServiceConstant.ODSERVICE.getOrderDetailByOrderId(om.getOrderId());
			resOM.setOdProducts(odProducts);
			
			if (checkCoupon != null) {
				resOM.setCheckCoupon(checkCoupon);
			} 			

			resOM.setUsedBonus(useBonus);
			resOM.setNowBonus(member.getBonus());
			
			resOM.setAddress(addressDetail.get("county").toString().replace("\"", "") + addressDetail.get("address").toString().replace("\"", ""));
//			System.out.println(resOM);
			
			String ecpay = gson.fromJson(req.getParameter("toEcpay"), String.class);
			// 一般結帳：結果轉出到前端，由前端儲存在Web sessionStroage後轉導頁面
//			if (ecpay == "false") {
				pw.println(gson.toJson(resOM)); // 一律送出資料後由前端submit到ecpay
//			} else {
//				res.sendRedirect("/Ecpay?orderId=" + om.getOrderId());
//				RequestDispatcher toEcpay = req.getRequestDispatcher("/Ecpay");
//				toEcpay.forward(req, res);
//				啟用Jedis相關服務，將最近一期訂單先進行儲存?等轉跳頁面再非同步請求近期訂單資訊?或是不顯示結果?
//				res.sendRedirect("/Ecpay?orderId=30");
//			}
		}
		
		if ("updateOM".equals(demand)) {
			Reader rd = req.getReader();
			BufferedReader brd = new BufferedReader(rd);
			String reqStr = brd.readLine();
			
			String omStr = reqStr.substring(reqStr.indexOf(":") + 1, reqStr.length() - 1);
			
			OrderMaster fromManager = gson.fromJson(omStr, OrderMaster.class);
			
			pw.println(OrderMasterServiceConstant.OMSERVICE.updateFromManager(fromManager));
			return;
		}
		
		if ("updateOMFromMember".equals(demand)) {
			Reader rd = req.getReader();
			BufferedReader brd = new BufferedReader(rd);
			String reqStr = brd.readLine();
			
			String omStr = reqStr.substring(reqStr.indexOf(":") + 1, reqStr.length() - 1);
			
			OrderMaster fromMember = gson.fromJson(omStr, OrderMaster.class);
			
			pw.println(OrderMasterServiceConstant.OMSERVICE.updateFromMember(fromMember));
			return;
		}

	}

}
