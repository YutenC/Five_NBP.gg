package com.secondhand.buy.controller;


import static com.core.util.CommonUtil.json2Pojo;
import static com.core.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/sh_shop/addbuyevent")
public class AddBuyEvent extends HttpServlet  {
	private static final long serialVersionUID = -4669764916210514485L;

	static private SecondHandBuyService service = new SecondHandBuyServiceImpl() ;
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		int buylistId = 0 ; 
		SecondHandBuylist buylist = null ;
		
		
		try {
			buylist = json2Pojo(req, SecondHandBuylist.class);
			if (buylist.getProductName().isEmpty() ||
				buylist.getContent().isEmpty() ||
				buylist.getApplicantBankNumber().isEmpty()) {
				throw new IOException() ;
			}
			service.insert(buylist);
			buylist.setSuccessful(true);
			buylistId = buylist.getBuylistId();
			if (buylist.isSuccessful()) {
				if (req.getSession(false) != null) {
					req.changeSessionId();
				}
				final HttpSession session = req.getSession();
				session.setAttribute("buylistId", buylistId);
			}
			
		} catch (Exception e) {
			buylist = new SecondHandBuylist();
			buylist.setSuccessful(false);
		}
		
		System.out.println(buylist.getImage().size());
		
		
		try {
			for (SecondHandBuyPicture img : buylist.getImage()) {
				System.out.println(buylistId);
				service.insertimg((SecondHandBuyPicture)img, buylistId); 
			}
			
		} catch (Exception e) {
		}
		
		
		
		
		
		writePojo2Json(resp, buylist);
	}
	
	
	

}
