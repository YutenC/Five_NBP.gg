package com.manager.controller;

import static com.manager.util.ManagerConstants.SERVICE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manager.entity.Manager;

@WebServlet("/manager/manager_remove")
public class ManagerRemoveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 從請求中獲取JSON數據
	    BufferedReader reader = request.getReader();
	    StringBuilder jsonBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonBody.append(line);
	    }
	    reader.close();
	    
	    // 解析JSON數據
	    JsonObject jsonObject = JsonParser.parseString(jsonBody.toString()).getAsJsonObject();
	    
	    Integer manager_id= jsonObject.get("manager_id").getAsInt();
	    
	    
	    // 在這裡執行相應的業務邏輯，例如將數據保存到數據庫中
	    SERVICE.remove(manager_id);
	  
	    
	    // 創建回應JSON數據
	    JsonObject responseJson = new JsonObject();
	    	      
	    responseJson.addProperty("successful", true); // 設置成功標誌，根據實際情況設置
	    responseJson.addProperty("redirectUrl", request.getContextPath() + "/html/manager_list.html"); // 設置重導的網址
	    
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
