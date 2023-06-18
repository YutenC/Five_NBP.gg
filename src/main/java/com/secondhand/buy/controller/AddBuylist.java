package com.secondhand.buy.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.util.CommonUtil;
import com.secondhand.buy.dao.impl.SecondHandBuylistDaoimpl;
import com.secondhand.buy.service.impl.SecondHandBuyServiceImpl;
import com.secondhand.buy.util.Json2vo;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;

@WebServlet("/secondhand/addbuylist")
public class AddBuylist extends HttpServlet {
	private static final long serialVersionUID = -4669764916210514485L;

	private SecondHandBuyServiceImpl service = new SecondHandBuyServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		SecondHandBuylist buylist = CommonUtil.json2Pojo(req, SecondHandBuylist.class);

		if (buylist == null) {
			buylist = new SecondHandBuylist();
			buylist.setProductName("無商品名稱");

			Json2vo.writevo2Json(resp, buylist);
			return;
		}
<<<<<<< HEAD


		buylist.setMemberId(1);
		buylist.setPayState(1);
		buylist.setApprovalState("0");

		service.insert(buylist);
		int buylistId = service.checkList(buylist.getBuylistId()).getBuylistId();
		int count = 0;

		for (SecondHandBuyPicture img : buylist.getImage()) {
			img.setBuylistId(buylistId);
			String[] imgbase = img.getImage().split(",");
			String[] name = imgbase[0].split(";")[0].split(":")[1].split("/"); // name[1] 是副檔名
			File file = new File("C:\\Users\\Tibame_T14\\Desktop\\AppImage");

			if (!file.exists()) {
				file.mkdirs();
			}

			String url = "C:\\Users\\Tibame_T14\\Desktop\\AppImage\\buyImage_" + buylistId + "_" + (++count) + "."
					+ name[1];
			byte[] data = Base64.getDecoder().decode(imgbase[1]);

			try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(url))) {

				bos.write(data);
				bos.flush();

			} catch (Exception e) {
			}

			service.insertimg(buylistId, url);

		}

		buylist.setSuccessful(true);
		CommonUtil.writePojo2Json(resp, service.checkList(buylist.getBuylistId()));

=======
		
		
		SecondHandBuylistDaoimpl sc = new SecondHandBuylistDaoimpl();
		SecondHandBuylist buylist2 = sc.selectById(13);
		buylist2.setProductName(buylist.getProductName());
		buylist2.setType(buylist.getType());
		buylist2.setContent(buylist.getContent());
		buylist2.setEstimate(buylist.getEstimate());
		buylist2.setApplicantBankNumber(buylist.getApplicantBankNumber());
		
		
		
		System.out.println(buylist2);
		sc.update(buylist2);
		
		
>>>>>>> origin/wangJZ
	}

}
