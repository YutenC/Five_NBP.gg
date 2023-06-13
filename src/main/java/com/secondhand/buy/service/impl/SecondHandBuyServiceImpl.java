package com.secondhand.buy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secondhand.buy.dao.impl.SecondHandBuylistDaoimpl;
import com.secondhand.buy.dao.impl.SecondHandBuylistPictureDaoimpl;
import com.secondhand.buy.service.SecondHandBuyService;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


@Service
public class SecondHandBuyServiceImpl implements SecondHandBuyService{

//	@Autowired
	private SecondHandBuylistDaoimpl dao = new SecondHandBuylistDaoimpl();

	
//	@Autowired
	private SecondHandBuylistPictureDaoimpl daoPic = new SecondHandBuylistPictureDaoimpl();
	
	
//	@Transactional
	@Override
	public boolean insert(SecondHandBuylist s) {
		dao.insert(s);
		return true;
	}



	@Override
	public SecondHandBuylist checkList(Integer i) {
		return dao.selectById(i);
	}


	@Transactional
	@Override
	public boolean insertimg(Integer id , String url) {
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


	
	
}