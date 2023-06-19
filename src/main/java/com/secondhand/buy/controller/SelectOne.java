package com.secondhand.buy.controller;

import static com.core.util.CommonUtil.json2Pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.util.CommonUtil;
import com.secondhand.buy.pojo.OneString;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


@WebServlet("/sh_shop/selectone")
public class SelectOne extends HttpServlet {
	private static final long serialVersionUID = -4362073464223691043L;

	static private SecondHandBuyService service = new SecondHandBuyServiceImpl() ;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = json2Pojo(req, OneString.class).getStr();
		try {
			SecondHandBuylist sl = service.selectOne(Integer.parseInt(id));
			List<SecondHandBuyPicture> imgList = service.selectimg(sl);

			for (SecondHandBuyPicture img : imgList) {
				File src = new File(img.getImage());
				String[] s = src.getName().split("/");
				img.setImage(s[s.length - 1]);
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
