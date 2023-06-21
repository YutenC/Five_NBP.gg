package com.shop.shoporder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

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
import com.shop.product.util.CouponServiceConstant;
import com.shop.product.util.ProductServiceConstant;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.util.MemberServiceConstant;
import com.shop.shoporder.util.OrderDetailServiceConstant;
import com.shop.shoporder.util.OrderMasterServiceConstant;
import com.shop.shoporder.util.ResOrderMaster;
import com.shop.shoporder.util.TransOrderProduct;
import com.shop.shopproduct.entity.Coupon;

@WebServlet("/OrderMaster")
public class OrderMasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderMasterController() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		
		HttpSession httpSession = req.getSession();
		
		Member login = new Member();
		login.setAccount("ReimuHakurei");
		login.setPassword("HakureiShrine");
		Member mber = MemberServiceConstant.MBSERVICE.login(login);
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
			req.setAttribute("trObjList", trObjList);
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
			om.setCommitDate(new java.sql.Date(date.getTime()));
			
			Integer commitType = 0;
			String payment = req.getParameter("payment");
			switch (payment) {
			case "credit":
				commitType = 1;
				om.setPayStatus(1);
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
			for (TransOrderProduct trObj : trObjList) {
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
			
			boolean addNewOM = OrderMasterServiceConstant.OMSERVICE.newOrder(om, trObjList, member);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/ShoppingList");
			dispatcher.include(req, res);
			
			ResOrderMaster resOM = new ResOrderMaster();
			List<TransOrderProduct> odProducts = OrderDetailServiceConstant.ODSERVICE.getOrderDetailByOrderId(om.getOrderId());
			resOM.setOdProducts(odProducts);
			
			if (checkCoupon != null) {
				resOM.setCheckCoupon(checkCoupon);
			} 			

			resOM.setUsedBonus(useBonus);
			resOM.setNowBonus(member.getBonus());
			
			resOM.setAddress(addressDetail.get("county").toString().replace("\"", "") + addressDetail.get("address").toString().replace("\"", ""));
//			System.out.println(resOM);
			
			String ecpay = req.getParameter("toEcpay");
			// 一般結帳：結果轉出到前端，由前端儲存在Web sessionStroage後轉導頁面
			if (ecpay == "false") {
				pw.println(gson.toJson(resOM));
			} else {
				
			}
		}
	}

}
