package com.secondhand.buy.controller;


import static com.core.util.CommonUtil.json2Pojo;
import static com.core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.buy.pojo.OneString;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/sh_shop/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = -3236355283493258651L;
	static private SecondHandBuyService service = new SecondHandBuyServiceImpl() ;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		SecondHandBuylist sl = json2Pojo(req , SecondHandBuylist.class);
		System.out.println(sl.getBuylistId());
		
		SecondHandBuylist sla = service.selectOne(sl.getBuylistId());
		sla.setProductName(sl.getProductName());
		sla.setType(sl.getType());
		sla.setContent(sl.getContent());
		sla.setEstimate(sl.getEstimate());
		sla.setPrice(sl.getPrice());
		sla.setPayState(sl.getPayState());
		sla.setApplicantBankNumber(sl.getApplicantBankNumber());
		
		System.out.println(sla);
		
		
		
		service.upDate(sla);
		
		
		writePojo2Json(resp, new OneString("修改成功"));
		
		
	}
	
}
