package com.secondhand.buy.service;

import java.util.List;

import com.core.service.CoreService;
import com.secondhand.buy.vo.SecondHandBuyPicture;
import com.secondhand.buy.vo.SecondHandBuylist;

public interface SecondHandBuyService extends CoreService {
	public boolean insert(SecondHandBuylist s);
	public SecondHandBuylist checkList(Integer i);
	public boolean insertimg(Integer i , String url);
	public List<SecondHandBuylist> selectAll() ;
	public List<SecondHandBuyPicture> selectimg(SecondHandBuylist s) ;
	public SecondHandBuylist selectOne(Integer i);
}
