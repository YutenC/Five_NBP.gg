package com.shop.shoporder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.entity.Member;
import com.shop.followlist.util.FollowListConstants;
import com.shop.followlist.util.ResFollowList;
import com.shop.shoporder.entity.OrderDetail;
import com.shop.shoporder.entity.PKOrderDeatail;
import com.shop.shoporder.util.MemberServiceConstant;
import com.shop.shoporder.util.OrderDetailServiceConstant;
import com.shop.shoporder.util.ResOrderDetail;
import com.shop.shoporder.util.TransOrderProduct;

@WebServlet("/OrderDetail")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderDetailController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	
    	Gson gson = new Gson();
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("application/json");
    	PrintWriter pw = res.getWriter();
    	
    	HttpSession httpSession = req.getSession();
    	Member login = new Member();
    	login.setAccount("ReimuHakurei");
    	login.setPassword("HakureiShrine");
    	Member member = MemberServiceConstant.MBSERVICE.login(login);
    	httpSession.setAttribute("member", member);
    	
    	Member getMember = (Member)httpSession.getAttribute("member");
    	Integer memberId;
    	
    	if (getMember == null) {
    		res.sendRedirect("/Five_NBP.gg");
    		return;
    	} else {
    		memberId = getMember.getMember_id();
    	}
    	
    	if (req.getParameter("getMemberAll") != null) {
    		Collection<ResOrderDetail> rsODList = OrderDetailServiceConstant.ODSERVICE.getMemberAllOrderDetail(memberId);
    		pw.println(gson.toJson(rsODList));
    		return;
    	}
    	
    	String orderStr = req.getParameter("getByOrderId");
    	if (orderStr != null) {
    		Integer orderId = Integer.valueOf(orderStr);
    		List<TransOrderProduct> trOPList = OrderDetailServiceConstant.ODSERVICE.getOrderDetailByOrderId(orderId);
    		pw.println(gson.toJson(trOPList));
    		return;
    	}
    	
	}
}
