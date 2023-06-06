package com.secondhand.buy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.buy.dao.impl.SecondHandBuylistDaoimpl;
import  com.secondhand.buy.util.Json2vo;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/secondhand/addbuylist")
public class AddBuylist extends HttpServlet  {
	private static final long serialVersionUID = -4669764916210514485L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		SecondHandBuylist buylist = Json2vo.jsonTovo(req, SecondHandBuylist.class);
		
		if (buylist == null) {
			buylist = new SecondHandBuylist();
			buylist.setProductName("無商品名稱");
			Json2vo.writevo2Json(resp, buylist);
			return;
		}
		
		buylist.setMemberId(1);
		buylist.setPayState(0);
		buylist.setApprovalState("000");
		
		
		SecondHandBuylistDaoimpl sc = new SecondHandBuylistDaoimpl();
		sc.insert(buylist);
		
		
	}

}
