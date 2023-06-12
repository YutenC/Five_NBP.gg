package com.followlist.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.followlist.entity.FollowList;

public interface FollowListDao extends CoreDao<FollowList, Integer>{
	
	FollowList selectByCompositePK(Integer memeberId, Integer productId);
	
	List<FollowList> selectByMemeberId(Integer memberId);
	
	int deleteByCompositePK(Integer memberId, Integer productId);
}
