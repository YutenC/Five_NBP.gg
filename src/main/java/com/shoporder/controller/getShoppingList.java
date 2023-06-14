package com.shoporder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.entity.Member;
import com.shoporder.util.ShoppingListConstants;
import com.shoporder.util.TransOrderProduct;

@WebServlet("/getShoppingList")
public class getShoppingList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getShoppingList() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Member odmember = new Member();
		odmember.setMember_id(1);
		httpSession.setAttribute("member", odmember);
		Member member = (Member)httpSession.getAttribute("member");
		Integer memberId = null;
		if (member != null) {
			memberId = member.getMember_id();
		} else {
			res.sendRedirect("/Five_NBP.gg");
			return;
		}
		
		Gson gson = new Gson();
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("application/json");
    	PrintWriter pw = res.getWriter();
   		List<TransOrderProduct> trspList = ShoppingListConstants.SLSERVICE.getAllShoppingList(memberId);
   		pw.println(gson.toJson(trspList));
	}


}
