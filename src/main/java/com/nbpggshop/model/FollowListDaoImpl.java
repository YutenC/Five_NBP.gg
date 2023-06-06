package com.nbpggshop.model;

import java.util.List;

import org.hibernate.Session;

public class FollowListDaoImpl implements FollowListDao {

	@Override
	public int insert(FollowList followList) {
		getSession().persist(followList);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		FollowList followList = session.get(FollowList.class, id);
		session.remove(followList);
		return 1;
	}

	@Override
	public int update(FollowList followList) {
		getSession().update(followList);
		return 1;
	}

	@Override
	public FollowList selectById(Integer id) {
		return getSession().get(FollowList.class, id);
	}

	@Override
	public List<FollowList> selectAll() {
		return getSession()
				.createQuery("FROM FollowList ORDER BY memberId", FollowList.class)
				.getResultList();
	}

	@Override
	public List<FollowList> selectByCompositePK(Integer memeberId, Integer productId) {
		String hql = "FROM FollowList WHERE memberId = :memberId AND prodcutId = : productId";
		return getSession()
				.createQuery(hql, FollowList.class)
				.setParameter("memberId", memeberId)
				.setParameter("productId", productId)
				.getResultList();
	}

}
