package com.secondhand.buy.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.util.CommonUtil;
import com.secondhand.buy.pojo.OneString;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/secondhand/selectone")
public class SelectOne extends HttpServlet {
	private static final long serialVersionUID = -4362073464223691043L;

	@Autowired
	private SecondHandBuyService service;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = CommonUtil.json2Pojo(req, OneString.class).getStr();
		try {
			SecondHandBuylist sl = service.selectOne(Integer.parseInt(id));
			List<SecondHandBuyPicture> imgList = service.selectimg(sl);

			for (SecondHandBuyPicture img : imgList) {
//				
//				
//				// 本機路徑 轉 Base64 再丟回去
//				File src = new File(img.getImage());
			File src = new File(img.getImage());
			String[] s = src.getName().split("/");
			img.setImage(s[s.length - 1]);
//				String type = src.getName().substring(src.getName().indexOf(".")+1);
//				
//				try( BufferedInputStream bis = new BufferedInputStream (new FileInputStream(src))) {
//					byte[] imgb = bis.readAllBytes();
//					img.setImage("data:image/"+type+";base64,"+Base64.getEncoder().encodeToString(imgb));
//					
//				} catch (Exception e) {
//					img.setImage("");
//					e.printStackTrace();
//					
//				}
//				
			}
			sl.setImage(imgList);

			CommonUtil.writePojo2Json(resp, sl);

		} catch (Exception e) {
			OneString error = new OneString();
			error.setStr("查無結果");
			CommonUtil.writePojo2Json(resp, error);
		}

	}

}
