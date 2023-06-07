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


@WebServlet("/html/addbuylist")
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
		
		
		SecondHandBuylistDaoimpl sc = new SecondHandBuylistDaoimpl();
		SecondHandBuylist buylist2 = sc.selectById(13);
		buylist2.setProductName(buylist.getProductName());
		buylist2.setType(buylist.getType());
		buylist2.setContent(buylist.getContent());
		buylist2.setEstimate(buylist.getEstimate());
		buylist2.setApplicantBankNumber(buylist.getApplicantBankNumber());
		
		
		
		System.out.println(buylist2);
		sc.update(buylist2);
		
		
	}

}
