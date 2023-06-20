package com.power_of_manager.controller;

import static com.power_of_manager.util.Power_of_ManagerConstants.SERVICE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.power.entity.Power;
import com.power.service.PowerService;
import com.power.service.impl.PowerServiceImpl;
import com.power_of_manager.entity.Power_of_Manager;

@WebServlet("/manager/pom_add")
public class PoMAddServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
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
//		傳manager_id 給servlet
//		傳一個checkedIdList<power_id> 給servlet 
	    JsonObject jsonObject = JsonParser.parseString(jsonBody.toString()).getAsJsonObject();
	    
	    Integer manager_id= jsonObject.get("manager_id").getAsInt();
	    
	    JsonArray checkedIdArray=  jsonObject.get("chekedIdList").getAsJsonArray();
	    List<Integer> checkedIdList = new ArrayList<>();
        for (int i = 0; i < checkedIdArray.size(); i++) {
            checkedIdList.add(checkedIdArray.get(i).getAsInt());
        }
	    
//    	servlet會先用 findAllId() 找出所有的power_id並做成list_allID <power_id>
        PowerService pService = new PowerServiceImpl();
        List<Power> powerList= pService.findAll();
        List<Integer> powerIdList = new ArrayList<>();
        for (Power power : powerList) {
            powerIdList.add(power.getPower_id());
        }
	    
	    
	    // 在這裡執行相應的業務邏輯，例如將數據保存到數據庫中
//    	for(id: list_allID)
//    	if (list_activeID.contain(id)){
//    		if(pom已有){
//    			nothing
//    		}else{
//    			insert/save (pom)
//    		}
//    	}else{
//    		if(pom已有){
//    			delete(pom)
//    		}else{
//    			nothing
//    		}
//    	}
	    
	    for (Integer power_id: powerIdList) {
	    	Power_of_Manager pom= new Power_of_Manager();
	    		    	
	    	Power_of_Manager.PK pk = new Power_of_Manager.PK();
	    	pk.manager_id = manager_id;
	    	pk.power_id = power_id;
	    	
	    	pom.setManager_id(pk.manager_id);
	    	pom.setPower_id(pk.power_id);
	    	
	    	
	    	if (checkedIdList.contains(power_id)) {
	    		SERVICE.savePower_of_Manager(pom);
	    	}else if (!(checkedIdList.contains(power_id)) && SERVICE.selectPower_of_Manager(pk)!= null) {
	    		SERVICE.deletePower_of_Manager(pk);
	    	}
	    }
        
        
        
        
	    
	    // 創建回應JSON數據
	    JsonObject responseJson = new JsonObject();
	    
	    
	    
	    responseJson.addProperty("successful", true); // 設置成功標誌，根據實際情況設置
	    responseJson.addProperty("redirectUrl", request.getContextPath() + "/manager/manager_list.html"); // 設置重導的網址
	    
	    // 設置回應的Content-Type為application/json
	    
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "0");
	    
	    response.setContentType("application/json");
	    
	    // 發送回應
	    PrintWriter writer = response.getWriter();
	    writer.println(responseJson.toString());
	    writer.close();
	}
	
	
}
