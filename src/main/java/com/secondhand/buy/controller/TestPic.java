package com.secondhand.buy.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/sh_shop/testpic")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 设置文件大小阈值
		maxFileSize = 1024 * 1024 * 5, // 设置最大文件大小
		maxRequestSize = 1024 * 1024 * 10 // 设置最大请求大小
)
public class TestPic extends HttpServlet {
	private static final long serialVersionUID = -6139457402422576192L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Collection<Part> parts = req.getParts();
		try {
			File file = new File("C:\\Users\\Tibame_T14\\Desktop\\AppImage");
			if (!file.exists()) {
				file.mkdirs();
			}
			for (Part part : parts) {
				
				String url = "C:\\Users\\Tibame_T14\\Desktop\\AppImage\\buyImage" + part.getName();
				try (InputStream reader = part.getInputStream();
					 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(url))) 
				{
					byte[] buffer = new byte[1024];
					int s;
					while ((s = reader.read(buffer)) != -1) {
						bos.write(buffer, 0, s);

					}
				} catch (Exception e) {
				}
			}
			
		} catch (Exception e) {}
		

	}

}
