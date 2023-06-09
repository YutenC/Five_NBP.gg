package com.manager.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.manager.dao.ManagerDao;
import com.manager.entity.Manager;

@Repository
public class ManagerDaoImpl implements ManagerDao{ 
	
	@Override
	public int insert(Manager manager) {
		getSession().persist(manager);
		
		return 1;
	}
	
	@Override
	public int deleteById(Integer manager_id) {
		Session session = getSession();
		Manager manager= session.load(Manager.class, manager_id);
		session.remove(manager);
		
		return 1;
	}
	
	@Override
	public int update(Manager manager) {
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Manager SET ");
		final String password = manager.getPassword();
		if (password != null && !password.isEmpty()) {
			hql.append("password = :password,");
		}
		hql.append("account = :account,")
			.append("name = :name,")
			.append("email = :email,")
			.append("phone = :phone,")
			.append("address = :address,")
			.append("is_working = :is_working ")
			.append("WHERE manager_id = :manager_id");
			
		Query query = getSession().createQuery(hql.toString());
		if (password != null && !password.isEmpty()) {
			query.setParameter("password", manager.getPassword());
		}
		return query.setParameter("name", manager.getName())
				.setParameter("email", manager.getEmail())
				.setParameter("phone", manager.getPhone())
				.setParameter("address", manager.getAddress())
				.setParameter("is_working", manager.getIs_working())
				.setParameter("account", manager.getAccount())
				.setParameter("manager_id", manager.getManager_id())
				.executeUpdate();
	}
	
	@Override
	public Manager selectById(Integer manager_id) {
		
		return getSession().get(Manager.class, manager_id);
	}
	
	@Override
	public List<Manager> selectAll() {
		final String hql = "FROM Manager ORDER BY manager_id";
		return getSession()
				.createQuery(hql, Manager.class)
				.getResultList();
	}
	
	@Override
	public Manager selectByUserName(String account) {
		Session session = getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Manager> criteriaQuery = criteriaBuilder.createQuery(Manager.class);
		Root<Manager> root = criteriaQuery.from(Manager.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("account"), account));
		return session.createQuery(criteriaQuery).uniqueResult();
	}
	
	@Override
	public Manager selectForLogin(String account, String password) {
		final String sql = "select * from MANAGER "
				+ "where ACCOUNT = :account and PASSWORD = :password";
		return getSession().createNativeQuery(sql, Manager.class)
				.setParameter("account", account)
				.setParameter("password", password)
				.uniqueResult();
	}
	
}
