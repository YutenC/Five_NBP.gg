package com.shop.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.entity.Member;
import com.shop.product.util.CouponServiceConstant;
import com.shop.shopproduct.entity.Coupon;

@WebServlet("/Coupon")
public class CouponController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CouponController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		
		Member mber = new Member();
		mber.setMember_id(1);
		
		HttpSession httpSession = req.getSession();
		
		httpSession.setAttribute("member", mber);
		
		Member member = (Member)httpSession.getAttribute("member");
		
		if (member == null) {
			res.sendRedirect("/Five_NBP.gg");
			return;
		}
		
		String couponCode = req.getParameter("couponCode");
		if (couponCode != null) {
			Coupon coupon = CouponServiceConstant.CPSERVICE.getCouponByDiscountCode(couponCode);
			pw.println(gson.toJson(coupon));
			return;
		}
		
	}


}
