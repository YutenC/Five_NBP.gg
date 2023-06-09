package com.manager.controller;

import static com.manager.util.ManagerConstants.SERVICE;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manager.entity.Manager;

@WebServlet("/manager/manager_list")
public class ManagerListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		List<Manager> managerList = SERVICE.findAll();
		
		// 使用 Gson 將 managerList 轉換為 JSON 字串
        Gson gson = new Gson();
        String json = gson.toJson(managerList);
        
        // 建立一個包含 managerList 屬性的物件
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("managerList", JsonParser.parseString(json));

        // 設置回應的 Content-Type 為 application/json
        resp.setContentType("application/json");

        // 將 JSON 字串寫入回應中
        resp.getWriter().write(jsonObject.toString());
		
//		req.setAttribute("managerList", managerList);
//		req.getRequestDispatcher( "/html/manager_list.html").forward(req, resp);
		
	}
	
}
