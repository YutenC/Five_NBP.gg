package com.secondhand.buy.dao;


import java.util.Date;
import java.util.List;

import com.core.dao.CoreDao;
import com.secondhand.buy.vo.SecondHandBuylist;



public interface SecondHandBuylistDao extends CoreDao<SecondHandBuylist, Integer> {

	List<SecondHandBuylist> selectByTime(Date starttime , Date endtime);
}
