package com.secondhand.buy.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.util.CommonUtil;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;

@WebServlet("/secondhand/selectall")
public class SelectAll extends HttpServlet {
	private static final long serialVersionUID = 5250110307973476208L;
	
	private SecondHandBuyServiceImpl service = new SecondHandBuyServiceImpl() ;
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<SecondHandBuylist> list = service.selectAll();
		for (SecondHandBuylist sl : list) {
			
			List<SecondHandBuyPicture> imgList = service.selectimg(sl);
				
				for (SecondHandBuyPicture img : imgList) {
//					
//					
//					// 本機路徑 轉 Base64 再丟回去
					File src = new File(img.getImage());
					String[] s = src.getName().split("/");
					img.setImage(s[s.length-1]);
//					String type = src.getName().substring(src.getName().indexOf(".")+1);
//					
//					try( BufferedInputStream bis = new BufferedInputStream (new FileInputStream(src))) {
//						byte[] imgb = bis.readAllBytes();
//						img.setImage("data:image/"+type+";base64,"+Base64.getEncoder().encodeToString(imgb));
//						
//					} catch (Exception e) {
//						img.setImage("");
//						e.printStackTrace();
//						
//					}
//					
				}
//				System.out.println(imgList);
				sl.setImage(imgList);
			
			
		}
		
		
		
		
		CommonUtil.writePojo2Json(resp, list);
	}

}
