package com.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.ManagerDao;
import com.manager.dao.impl.ManagerDaoImpl;
import com.manager.entity.Manager;
import com.manager.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	private ManagerDao dao;

	public ManagerServiceImpl() {
		dao = new ManagerDaoImpl();
	}
	
	@Override
	public Manager register(Manager manager) {
		if (manager.getAccount() == null) {
			manager.setMessage("帳號未輸入");
			manager.setSuccessful(false);
			return manager;
		}

		if (manager.getPassword() == null) {
			manager.setMessage("密碼未輸入");
			manager.setSuccessful(false);
			return manager;
		}

		if (manager.getName() == null) {
			manager.setMessage("名字未輸入");
			manager.setSuccessful(false);
			return manager;
		}

		if (dao.selectByUserName(manager.getAccount()) != null) {
			manager.setMessage("帳號重複");
			manager.setSuccessful(false);
			return manager;
		}

		manager.setIs_working(1);
		
		final int resultCount = dao.insert(manager);
		if (resultCount < 1) {
			manager.setMessage("註冊錯誤，請聯絡管理員!");
			manager.setSuccessful(false);
			return manager;
		}

		manager.setMessage("註冊成功");
		manager.setSuccessful(true);
		return manager;
	}
	
	@Override
	public Manager login(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Manager selectManager(Integer manager_id) {
		Manager manager= dao.selectById(manager_id);
		return manager;
	}
	
	@Override
	public Manager edit(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Manager> findAll() {
		return dao.selectAll();
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
