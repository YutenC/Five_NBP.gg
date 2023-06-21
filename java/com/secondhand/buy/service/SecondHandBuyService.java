package com.secondhand.buy.service;

import java.util.List;

import com.core.service.CoreService;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;


public interface SecondHandBuyService extends CoreService {
	public boolean insert(SecondHandBuylist s);
	
	public boolean insertimg(SecondHandBuyPicture img , Integer id);
	
	public SecondHandBuylist selectOne(Integer i);
	
	public List<SecondHandBuylist> selectAll() ;
	
	public List<SecondHandBuyPicture> selectimg(SecondHandBuylist s) ;
	
	public boolean delImg(SecondHandBuyPicture s);
	
	public boolean delbuyList(SecondHandBuylist sl);
	
	public boolean upDate(SecondHandBuylist sl);
	
	
	
}
