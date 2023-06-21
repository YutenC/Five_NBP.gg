package com.manager.dao;

import com.core.dao.CoreDao;
import com.manager.entity.Manager;

public interface ManagerDao extends CoreDao<Manager, Integer> {
	
	Manager selectByUserName(String account);        // 找管理員的帳號

    Manager selectForLogin(String account, String password);          // 找登入的管理員

}
