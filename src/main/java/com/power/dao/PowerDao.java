package com.power.dao;

import com.core.dao.CoreDao;
import com.power.entity.Power;

public interface PowerDao extends CoreDao<Power, Integer>{
	
	Power selectByPowerName(String power_name);        // 找管理員的帳號

}
