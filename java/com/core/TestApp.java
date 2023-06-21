package com.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.core.util.HibernateUtil;
import com.manager.entity.Manager;

public class TestApp {
public static void main(String[] args) {
//	Manager manager = new Manager();
//	manager.setManager_id();
//	manager.setAccount("1111111111");
//	manager.setPassword("2222222222");
//	manager.setName("紅燒");
//	manager.setEmail("sssss");
//	manager.setPhone("22222");
//	manager.setAddress("adasdasdas");
//	manager.setIs_working(1);
	
	
//	ManagerDao managerdao = new ManagerDaoImpl();
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    System.out.println(session);
    System.out.println(session);
//    Manager manager = session.get(Manager.class, 1);
//    System.out.println(manager.getAccount());
	
	}

}
