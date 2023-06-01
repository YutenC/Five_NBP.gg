package com.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.ManagerDao;
import com.manager.entity.Manager;
import com.manager.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private  ManagerDao dao;
	
	@Override
	public Manager register(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Manager login(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Manager edit(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Manager> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean remove(Integer manager_id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean save(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Manager changeWorkingState(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
