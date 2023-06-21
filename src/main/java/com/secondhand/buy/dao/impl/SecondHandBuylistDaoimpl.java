package com.secondhand.buy.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.secondhand.buy.dao.SecondHandBuylistDao;
import com.secondhand.buy.vo.SecondHandBuylist;


@Repository
public class SecondHandBuylistDaoimpl implements SecondHandBuylistDao {
	
	@PersistenceContext
	private Session session;

	@Override
	public int insert(SecondHandBuylist buylist) {
		session.persist(buylist);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		SecondHandBuylist buylist = session.get(SecondHandBuylist.class, id);
		session.remove(buylist);
		return 1;
	}


	@Override
	public int update(SecondHandBuylist buylist) {
		session.update("SecondHandBuylist", buylist);
		return 1;
	}
	@Override
	public SecondHandBuylist selectById(Integer id) {
		final String sql = "SELECT * FROM secondhand_buylist where buylist_id = :id  ";
		return session.createNativeQuery(sql, SecondHandBuylist.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public List<SecondHandBuylist> selectAll() {
		final String sql = "SELECT * FROM secondhand_buylist ";
		return session.createNativeQuery(sql, SecondHandBuylist.class).getResultList();
	}

	@Override
	public List<SecondHandBuylist> selectByTime(Date starttime, Date endtime) {

		final String sql = "SELECT * FROM secondhand_buylist WHERE  Apply_time BETWEEN :starttime AND :endtime";
		return session.createNativeQuery(sql, SecondHandBuylist.class).setParameter("starttime", starttime)
				.setParameter("endtime", endtime).getResultList();
	}

}
