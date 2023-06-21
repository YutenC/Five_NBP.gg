package com.shoporder.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.shoporder.dao.OrderMasterDao;
import com.shoporder.entity.OrderMaster;

public class OrderMasterDaoImpl implements OrderMasterDao {

	@Override
	public int insert(OrderMaster orderMaster) {
		getSession().persist(orderMaster);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		OrderMaster orderMaster = session.get(OrderMaster.class, id);
		session.remove(orderMaster);
		return 1;
	}

	@Override
	public int update(OrderMaster orderMaster) {
		getSession().update(orderMaster);
		return 1;
	}

	@Override
	public OrderMaster selectById(Integer id) {
		return getSession()
				.get(OrderMaster.class, id);
	}

	@Override
	public List<OrderMaster> selectAll() {
		return getSession()
				.createQuery("FROM OrderMaster ORDER BY orderId", OrderMaster.class)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByMemberId(Integer memberId) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderMaster> criteriaQuery = criteriaBuilder.createQuery(OrderMaster.class);
		Root<OrderMaster> root = criteriaQuery.from(OrderMaster.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("memberId"), memberId));
		return getSession()
				.createQuery(criteriaQuery)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByCommitDate(Date lower, Date upper) {
		String hql = "FROM orderMaster WHERE commitDate BETWEEN :lower AND :upper";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("lower", lower)
				.setParameter("upper", upper)
				.getResultList();
	}

	@Override
	public OrderMaster selectByDeliverNumber(String deliverNumber) {
		String hql = "FROM OrderMaster WHERE deliverNumber = :deliverNumber";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("deliverNumber", deliverNumber)
				.uniqueResult();
	}

	@Override
	public List<OrderMaster> selectByCommitType(Integer commitType) {
		String hql = "FROM OrderMaster WHERE commitType = :commitType";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("commitType", commitType)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByDeliverState(Integer deliverState) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderMaster> criteriaQuery = criteriaBuilder.createQuery(OrderMaster.class);
		Root<OrderMaster> root = criteriaQuery.from(OrderMaster.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("deliverState"), deliverState));
		return getSession()
				.createQuery(criteriaQuery)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByDeliverLocation(String location) {
		String hql = "FROM OrderMaster WHERE deliverLocation LIKE '%'||:deliverLocaiton||'%'";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("deliverLocation", location)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByPickType(Integer pickType) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderMaster> criteriaQuery = criteriaBuilder.createQuery(OrderMaster.class);
		Root<OrderMaster> root = criteriaQuery.from(OrderMaster.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("pickType"), pickType));
		return getSession()
				.createQuery(criteriaQuery)
				.getResultList();
	}

	@Override
	public OrderMaster selectByCouponId(Integer couponId) {
		String hql = "FROM OrderMaster WHERE couponId = :couponId";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("couponId", couponId)
				.uniqueResult();
	}

	@Override
	public List<OrderMaster> selectByOrderStatus(Integer orderstatus) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderMaster> criteriaQuery = criteriaBuilder.createQuery(OrderMaster.class);
		Root<OrderMaster> root =criteriaQuery.from(OrderMaster.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("orderStatus"), orderstatus));
		return getSession()
				.createQuery(criteriaQuery)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectByPaystatus(Integer payStatus) {
		String hql = "FROM OrderMaster WHERE payStatus = :payStatus";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("payStatus", payStatus)
				.getResultList();
	}

	@Override
	public List<OrderMaster> selectAllWithLimitAndOffset(Integer limit, Integer offset) {
		String hql = "FROM OrderMaster ORDER BY commitDate";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}

	@Override
	public long countdataNum(String condition, Integer id) {
		StringBuffer nql = new StringBuffer();
		nql.append("SELECT COUNT(*) FROM OrderMaster ");
		if (condition != null) {
			nql.append("WHERE " + condition + " = :" + condition);
		}
		
		Query<Long> query = getSession().createQuery(nql.toString(), Long.class);
		if (condition != null) {
			query.setParameter(condition, id);
		}
		
		return query.uniqueResult();
	}
	
}
