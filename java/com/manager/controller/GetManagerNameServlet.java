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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manager.entity.Manager;

@WebServlet("/manager/getName")
public class GetManagerNameServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
            // 讀取請求的內容
            
			HttpSession session= request.getSession(false);
			Manager loggedManager= (Manager) session.getAttribute("manager");

            // 在此處根據 manager 做相應的處理
            
            String loggedManager_name= loggedManager.getName();
            
            

            // 將回應返回給前端
            Gson gson = new Gson();
            String json = gson.toJson(loggedManager_name);
            
            JsonObject responseJson = new JsonObject();
            responseJson.add("loggedManager_name", JsonParser.parseString(json));
            
            // 在 responseJson 中設置相應的資料
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.println(responseJson.toString());
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            // 處理例外情況
        }
		
		
	}
}
