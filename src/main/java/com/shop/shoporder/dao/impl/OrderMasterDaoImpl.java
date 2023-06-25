package com.shop.shoporder.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.member.entity.Member;
import com.shop.shoporder.dao.OrderMasterDao;
import com.shop.shoporder.entity.OrderMaster;

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
	public List<OrderMaster> selectByCouponId(Integer couponId) {
		String hql = "FROM OrderMaster WHERE couponId = :couponId";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setParameter("couponId", couponId)
				.getResultList();
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
		String hql = "FROM OrderMaster ORDER BY commitDate DESC";
		return getSession()
				.createQuery(hql, OrderMaster.class)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}

	@Override
	public long simpleCountDatNum() {
		String hql = "SELECT COUNT(*) FROM OrderMaster";
		return getSession()
				.createQuery(hql, Long.class)
				.uniqueResult();
	}

	@Override
	public long countdataNumWithCondition(Map<String, Integer> condition) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(*) FROM OrderMaster ");
		for (String key : condition.keySet()) {
				hql.append("WHERE " + key + " = :" + key + " AND ");
		}
		
		hql.delete(hql.lastIndexOf("AND"), hql.length());

		Query<Long> query = getSession().createQuery(hql.toString(), Long.class);
		
		for (String key : condition.keySet()) {
			query.setParameter(key, condition.get(key));
		}
		
		return query.uniqueResult();
	}

	@Override
	public List<OrderMaster> selectWithConditionAndLimitOffset
	(Map<String, Integer> whereCondition, Map<String, Integer> limitAndOffset) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM OrderMaster WHERE ");
		
		for (String conditon : whereCondition.keySet()) {
			sb.append(conditon + " =:" + conditon + " AND ");
		}
		
		sb.delete(sb.lastIndexOf("AND"), sb.length());
		sb.append("ORDER BY commitDate DESC");
		Query<OrderMaster> query = getSession().createQuery(sb.toString());
		for (String condition : whereCondition.keySet()) {
			query.setParameter(condition, whereCondition.get(condition));
		}
		
		query.setFirstResult(limitAndOffset.get("OFFSET"));
		query.setMaxResults(limitAndOffset.get("LIMIT"));
		
		return query.getResultList();
	}

	@Override
	public List<OrderMaster> selectOrderbyConditionAndLimitOffset
	(Map<String, String> orderbyAndDescOrAsc, Map<String, Integer> limitAndOffset) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM OrderMaster ORDER BY " + orderbyAndDescOrAsc.get("orderBy"));
		sb.append(" " + orderbyAndDescOrAsc.get("orderWay") + " ");

		Query<OrderMaster> query = getSession().createQuery(sb.toString(), OrderMaster.class);
		
		query.setFirstResult(limitAndOffset.get("offset"));
		query.setMaxResults(limitAndOffset.get("limit"));
		
		return query.getResultList();
	}

	@Override
	public List<Member> selectLikeMemberName(String keyword) {
		String hql = "FROM Member WHERE nick LIKE " + keyword;
		
		return getSession()
				.createQuery(hql, Member.class)
				.getResultList();
	}

}	
	