package com.shop.followlist.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.shop.followlist.entity.FollowList;

public interface FollowListDao extends CoreDao<FollowList, Integer>{
	
	List<FollowList> selectByMemeberId(Integer memberId);
	
	boolean deleteByCompositePK(FollowList flist);
}
