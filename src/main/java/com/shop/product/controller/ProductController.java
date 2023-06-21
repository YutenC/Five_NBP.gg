package com.product.controller;

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
import com.product.util.ProductServiceConstant;
import com.shoporder.util.TransOrderProduct;

@WebServlet("/Product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
			
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		Gson gson = new Gson();
		PrintWriter pw = res.getWriter();
		
		HttpSession httpSession = req.getSession();
		Member mber = new Member();
		mber.setMember_id(1);
		httpSession.setAttribute("member", mber);
		
		Member member = (Member)httpSession.getAttribute("member");
		if (member == null) {
			res.sendRedirect("/Five_NBP.gg");
			return;
		}
		
		if (req.getParameter("getOneProduct") != null) {
			Integer productId = Integer.valueOf(req.getParameter("getOneProduct"));
			TransOrderProduct trpd = ProductServiceConstant.PDSERVICE.getOneProduct(productId);
			pw.println(gson.toJson(trpd));
			return;
		}
	}


}
