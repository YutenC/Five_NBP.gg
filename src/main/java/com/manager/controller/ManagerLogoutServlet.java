package com.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/manager/logout")
public class ManagerLogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		
		// 創建回應JSON數據
	    JsonObject responseJson = new JsonObject();
	    responseJson.addProperty("successful", false);
	    
		if (request.getSession(false)== null) {
			responseJson.addProperty("successful", true); // 設置成功標誌，根據實際情況設置
		}
		
//	     設置回應的Content-Type為application/json    
	    response.setContentType("application/json");
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "0");
	    
	 // 發送回應
	    PrintWriter writer = response.getWriter();
	    writer.println(responseJson.toString());
	    writer.close();
		
	}

}
