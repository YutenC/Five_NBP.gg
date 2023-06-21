package com.manager.controller;

import static com.manager.util.ManagerConstants.SERVICE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manager.entity.Manager;

@WebServlet("/manager/manager_logining")
public class ManagerLoginServlet extends HttpServlet {
	
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
	    
	    String manager_account= jsonObject.get("manager_account").getAsString();
	    String manager_password= jsonObject.get("manager_password").getAsString();
	    
	    Manager manager= new Manager();
	    manager.setAccount(manager_account);
	    manager.setPassword(manager_password);
	    
	    // 在這裡執行相應的業務邏輯，例如將數據保存到數據庫中
	    manager= SERVICE.login(manager);
	    
	    if (manager.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("manager_loggedin", true);
			session.setAttribute("manager", manager);
		}
	    
	    // 創建回應JSON數據
	    JsonObject responseJson = new JsonObject();
	    responseJson.addProperty("successful", manager.isSuccessful()); // 設置成功標誌，根據實際情況設置
	    responseJson.addProperty("redirectUrl", (String) request.getSession().getAttribute("location")); // 設置重導的網址
	    
	    responseJson.addProperty("manager_id", manager.getManager_id());
	    responseJson.addProperty("account", manager.getAccount());
	    
	    
	    // 設置回應的Content-Type為application/json
	    response.setContentType("application/json");
	    
	    // 發送回應
	    PrintWriter writer = response.getWriter();
	    writer.println(responseJson.toString());
	    writer.close();
	}

}
