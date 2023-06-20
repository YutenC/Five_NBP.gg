package com.power.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.power.dao.PowerDao;
import com.power.dao.impl.PowerDaoImpl;
import com.power.entity.Power;
import com.power.service.PowerService;

@Service
public class PowerServiceImpl implements PowerService {
	
	private PowerDao dao;
	
	public PowerServiceImpl() {
		dao= new PowerDaoImpl();
	}
	
	
	@Override
	public Power selectPowerByPowerName(String power_name) {
		Power power= dao.selectByPowerName(power_name);
		return power;
	}
	
	@Override
	public List<Power> findAll() {
		return dao.selectAll();
	}
}
