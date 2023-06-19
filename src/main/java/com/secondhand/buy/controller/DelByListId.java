package com.secondhand.buy.controller;

import static com.core.util.CommonUtil.json2Pojo;
import static com.core.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.buy.pojo.OneString;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/sh_shop/delbyid")
public class DelByListId extends HttpServlet  {
	private static final long serialVersionUID = -1806851938916882849L;

	static private SecondHandBuyService service = new SecondHandBuyServiceImpl() ;
	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
		String id = json2Pojo(req, OneString.class).getStr();
		try {
			SecondHandBuylist sl = service.selectOne(Integer.parseInt(id));
			List<SecondHandBuyPicture> imgList = service.selectimg(sl);

			for (SecondHandBuyPicture img : imgList) {
				service.delImg(img);
			}
			
			service.delbuyList(sl);
			OneString secc = new OneString();
			secc.setStr("刪除成功");
			writePojo2Json(resp, secc);
			
			
		}catch (Exception e) {
			OneString error = new OneString();
			error.setStr("刪除失敗");
			writePojo2Json(resp, error);
		}
		
		
	}

}
