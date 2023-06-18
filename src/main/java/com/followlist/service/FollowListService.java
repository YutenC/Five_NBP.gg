package com.followlist.service;

import java.util.List;

import com.core.service.CoreService;
import com.followlist.util.ResFollowList;

public interface FollowListService extends CoreService{
	
	List<ResFollowList> getAllFollowProduct(Integer memberId);
	
	boolean deleteFollowList(Integer memberId, Integer productId);
}
