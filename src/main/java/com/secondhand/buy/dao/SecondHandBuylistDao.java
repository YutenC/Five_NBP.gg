package com.secondhand.buy.dao;


import java.util.Date;
import java.util.List;

import com.core.dao.CoreDao;

public interface SecondHandBuylistDao<V, I> extends CoreDao<V, I> {

	List<V> selectByTime(Date starttime , Date endtime);
}
