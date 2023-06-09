package com.followproduct.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.followproduct.dao.FollowProductDao;
import com.followproduct.entity.FollowProduct;

public class FollowProductDaoImpl implements FollowProductDao {

	@Override
	public int insert(FollowProduct followList) {
		getSession().persist(followList);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		getSession().remove(getSession().get(FollowProduct.class, id));
		return 1;
	}

	@Override
	public int update(FollowProduct followList) {
		getSession().update(followList);
		return 1;
	}

	@Override
	public FollowProduct selectById(Integer id) {
		return getSession().get(FollowProduct.class, id);
	}

	@Override
	public List<FollowProduct> selectAll() {
		String hql = "FROM FollowList ORDER BY productId";
		return getSession().createQuery(hql, FollowProduct.class).getResultList();
	}

	@Override
	public List<FollowProduct> selectByCompositePK(Integer memeberId, Integer productId) {
		String hql = "FROM FollowList WHERE memberId = :memberId AND prodcutId = : productId";
		return getSession().createQuery(hql, FollowProduct.class)
					.setParameter("memberId", memeberId)
					.setParameter("productId", productId)
					.getResultList();
	}

	@Override
	public List<FollowProduct> selectByMemeberId(Integer memberId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<FollowProduct> cq = cb.createQuery(FollowProduct.class);
		Root<FollowProduct> root = cq.from(FollowProduct.class);
		cq.where(cb.equal(root.get("memberId"), memberId));
		return getSession().createQuery(cq).getResultList();
	}
	
}
