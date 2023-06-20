package com.power.service;

import java.util.List;

import com.core.service.CoreService;
import com.power.entity.Power;


public interface PowerService extends CoreService{

	Power selectPowerByPowerName(String power_name); // 用ID找power
	
	List<Power> findAll(); //查總表
}
