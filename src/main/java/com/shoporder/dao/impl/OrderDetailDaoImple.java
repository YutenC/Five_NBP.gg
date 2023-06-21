package com.shoporder.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.shoporder.dao.OrderDetailDao;
import com.shoporder.entity.OrderDetail;

public class OrderDetailDaoImple implements OrderDetailDao {

	@Override
	public int insert(OrderDetail orderDetail) {
		getSession().persist(orderDetail);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		OrderDetail orderDetail = session.get(OrderDetail.class, id);
		session.remove(orderDetail);
		return 1;
	}

	@Override
	public int update(OrderDetail orderDetail) {
		getSession().update(orderDetail);
		return 1;
	}

	@Override
	public OrderDetail selectById(Integer id) {
		return getSession().get(OrderDetail.class, id);
	}

	@Override
	public List<OrderDetail> selectAll() {
		return getSession()
				.createQuery("FROM OrderDetail ORDER BY orderId", OrderDetail.class)
				.getResultList();
	}

	@Override
	public OrderDetail selectByCompositePK(Integer productId, Integer orderId) {
		String hql = "FROM OrderDetail WHERE productId = :productId AND orderId = :orderId";
		return getSession()
				.createQuery(hql, OrderDetail.class)
				.setParameter("productId", productId)
				.setParameter("orderId", orderId)
				.uniqueResult();
	}

	@Override
	public OrderDetail selectByManagerId(Integer managerId) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<OrderDetail> criteriaQuery = criteriaBuilder.createQuery(OrderDetail.class);
		Root<OrderDetail> root = criteriaQuery.from(OrderDetail.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("managerId"), managerId));
		return getSession()
				.createQuery(criteriaQuery)
				.uniqueResult();
	}

	@Override
	public List<OrderDetail> selectByOrderId(Integer orderId) {
		String hql = "FROM OrderDetail WHERE pkOrderDeatail.orderId = :orderId";
		return getSession()
				.createQuery(hql, OrderDetail.class)
				.setParameter("orderId", orderId)
				.getResultList();
	}

}
