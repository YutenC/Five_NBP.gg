package com.followproduct.dao;

import java.util.List;

import com.core.dao.CoreDao;
import com.followproduct.entity.FollowProduct;

public interface FollowProductDao extends CoreDao<FollowProduct, Integer>{
	
	List<FollowProduct> selectByCompositePK(Integer memeberId, Integer productId);
	
	List<FollowProduct> selectByMemeberId(Integer memberId);
}
