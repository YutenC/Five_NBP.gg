package com.shop.followlist.controller;
//package com.followlist.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.followlist.service.FollowListService;
//import com.followlist.util.FollowListConstants;
//import com.google.gson.Gson;
//import com.member.entity.Member;
//
//@WebServlet("/deleteFollowList")
//public class deleteFollowList extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public deleteFollowList() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession httpSession = req.getSession();
//		req.setCharacterEncoding("UTF-8");
//    	Member member = new Member();
//    	member.setMember_id(1);
//    	httpSession.setAttribute("member", member);
//    	Member getMember = (Member)httpSession.getAttribute("member");
//    	Integer memberId = null;
//    	if (getMember != null) {
//    		memberId = getMember.getMember_id();
//    	} else {
//    		res.sendRedirect("/Five_NBP.gg");
//    		return;
//    	}
//    	
//    	Integer productId = Integer.valueOf(req.getParameter("productId"));
//    	boolean result = FollowListConstants.FPSERVICE.deleteFollowList(memberId, productId);
//    	
//    	Gson gson = new Gson();
//    	res.setCharacterEncoding("UTF-8");
//    	res.setContentType("application/json");
//    	PrintWriter pw = res.getWriter();
//    	pw.print(gson.toJson(result));
//	}
//
//}
