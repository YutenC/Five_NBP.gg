package com.power.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.power.dao.PowerDao;
//import com.manager.entity.Manager;
import com.power.entity.Power;

@Repository
public class PowerDaoImpl implements PowerDao {
	@Override
	public int insert(Power power) {
		getSession().persist(power);
		
		return 1;
	}
	
	@Override
	public int deleteById(Integer power_id) {
		Session session = getSession();
		Power power= session.load(Power.class, power_id);
		session.remove(power);
		
		return 1;
	}
	
	@Override
	public int update(Power power) {
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Power SET ");	
		hql.append("power_name = :power_name,")
			.append("power_content = :power_content,")
			.append("WHERE power_id = :power_id");
			
		Query query = getSession().createQuery(hql.toString());
		return query.setParameter("power_name", power.getPower_name())
				.setParameter("power_content", power.getPower_content())
				.setParameter("power_id", power.getPower_id())
				.executeUpdate();
	}
	
	@Override
	public Power selectById(Integer power_id) {
		
		return getSession().get(Power.class, power_id);
	}
	
	@Override
	public List<Power> selectAll() {
		final String hql = "FROM Power ORDER BY power_id";
		return getSession()
				.createQuery(hql, Power.class)
				.getResultList();
	}
	
	@Override
	public Power selectByPowerName(String power_name) {
		Session session = getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Power> criteriaQuery = criteriaBuilder.createQuery(Power.class);
		Root<Power> root = criteriaQuery.from(Power.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("power_name"), power_name));
		return session.createQuery(criteriaQuery).uniqueResult();
	}
	
}
