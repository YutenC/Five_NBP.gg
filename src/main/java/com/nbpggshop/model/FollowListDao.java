package com.nbpggshop.model;

import java.util.List;

import com.core.dao.CoreDao;

public interface FollowListDao extends CoreDao<FollowList, Integer>{
	
	List<FollowList> selectByCompositePK(Integer memeberId, Integer productId);
}
