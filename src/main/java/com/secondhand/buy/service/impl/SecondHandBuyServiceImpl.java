package com.secondhand.buy.service.impl;

import java.util.List;

import com.secondhand.buy.dao.SecondHandBuylistDao;
import com.secondhand.buy.dao.SecondHandBuylistPictureDao;
import com.secondhand.buy.dao.impl.SecondHandBuylistDaoimpl;
import com.secondhand.buy.dao.impl.SecondHandBuylistPictureDaoimpl;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


public class SecondHandBuyServiceImpl implements SecondHandBuyService{


	static private SecondHandBuylistDao dao = new SecondHandBuylistDaoimpl();
	static private SecondHandBuylistPictureDao daoPic = new SecondHandBuylistPictureDaoimpl();
	
	
	
	
	
	@Override
	public boolean insert(SecondHandBuylist s) {
		
		s.setMemberId(1);			
		s.setPayState(0);			// 預設為 0
		s.setApprovalState("0");	// 預設為 0
		dao.insert(s);
		return true;
	}


	@Override
	public boolean insertimg(SecondHandBuyPicture img ,Integer id) {
		String url = "C:\\Users\\Tibame_T14\\Desktop\\AppImage\\buyImage"+img.getImage();
		SecondHandBuyPicture pic = new SecondHandBuyPicture();
		pic.setBuylistId(id);
		pic.setImage(url);
		daoPic.insert(pic);
		return true;
	}
	
	
	



	@Override
	public List<SecondHandBuylist> selectAll() {
		return dao.selectAll();
	}



	@Override
	public List<SecondHandBuyPicture> selectimg(SecondHandBuylist s) {
		return daoPic.selectBylistId(s.getBuylistId());
	}



	@Override
	public SecondHandBuylist selectOne(Integer i) {
		return dao.selectById(i);
	}


	@Override
	public boolean delImg(SecondHandBuyPicture s) {
		daoPic.deleteById(s.getImageId());
		return true;
	}


	@Override
	public boolean delbuyList(SecondHandBuylist sl) {
		dao.deleteById(sl.getBuylistId());
		return true;
	}


	@Override
	public boolean upDate(SecondHandBuylist sl) {
		dao.update(sl);
		return true;
	}
	
	
	
	
	
	


	
	
}