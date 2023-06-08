package com.followproduct.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.followproduct.entity.FollowList;

public interface FollowListDao extends CoreDao<FollowList, Integer>{
	
	List<FollowList> selectByCompositePK(Integer memeberId, Integer productId);
}
