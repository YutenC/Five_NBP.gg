package com.secondhand.buy.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.secondhand.buy.vo.SecondHandBuyPicture;

public interface SecondHandBuylistPictureDao extends CoreDao<SecondHandBuyPicture, Integer> {
	public List<SecondHandBuyPicture> selectBylistId(Integer  i);
}
