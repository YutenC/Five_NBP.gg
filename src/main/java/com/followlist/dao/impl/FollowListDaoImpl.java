package com.followlist.dao.impl;

import java.util.List;

import com.followlist.dao.FollowListDao;
import com.followlist.entity.FollowList;

public class FollowListDaoImpl implements FollowListDao {

	@Override
	public int insert(FollowList followList) {
		getSession().persist(followList);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
//		getSession().remove(getSession().get(FollowList.class, id));
		return 0;
	}

	@Override
	public boolean deleteByCompositePK(FollowList flist) {
		getSession().remove(flist);
		return true;
	}

	@Override
	public int update(FollowList followList) {
		getSession().update(followList);
		return 1;
	}

	@Override
	public FollowList selectById(Integer id) {
		return null; //getSession().get(FollowList.class, id);
	}

	@Override
	public List<FollowList> selectAll() {
		String hql = "FROM FollowList ORDER BY productId";
		return getSession().createQuery(hql, FollowList.class).getResultList();
	}

	@Override
	public List<FollowList> selectByMemeberId(Integer memberId) {
		String hql = "FROM FollowList WHERE pkFollowList.memberId = :memberId";
		return getSession().createQuery(hql, FollowList.class).setParameter("memberId", memberId).getResultList();
	}
	
}
