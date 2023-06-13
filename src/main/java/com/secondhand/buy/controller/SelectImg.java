package com.secondhand.buy.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondhand/selectimg")
public class SelectImg extends HttpServlet  {
	private static final long serialVersionUID = 7319458959906666937L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		File src = new File("C:\\Users\\Tibame_T14\\Desktop\\AppImage\\"+ req.getParameter("imgname"));
		
//		String type = src.getName().substring(src.getName().indexOf(".")+1);
		
		resp.setContentType("image/gif");
		
		
		try(ServletOutputStream out = resp.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream (new FileInputStream(src))) {
			
			out.write(bis.readAllBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		
	}

}
