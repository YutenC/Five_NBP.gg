package com.shop.shoporder.service;

import java.util.List;
import java.util.Map;

import com.core.service.CoreService;
import com.member.entity.Member;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.util.ManageOrder;
import com.shop.shoporder.util.MemberViewOrder;
import com.shop.shoporder.util.OrderSelection;
import com.shop.shoporder.util.TransOrderProduct;

public interface OrderMasterService extends CoreService{
	
	boolean newOrder(OrderMaster om, List<TransOrderProduct> trObjList, Member member);
	
	List<ManageOrder> showAllMgOrderList(Integer limit, Integer offset);
	
	/**
	 * 
	 * @param orderByCondition(Map<String, String>): order by column and desc or not
	 * @param limitAndOffset(Map<String, Integer>): limt: value, offset: value
	 * @return
	 */
	List<ManageOrder> showMgOrderListSortedWithLimitOffset(Map<String, String> orderByCondition, Map<String, Integer> limitAndOffset);
	
	List<ManageOrder> getJedisOrderMasterResults(OrderSelection selectionCode, Integer sortWay, Integer offset);

	List<ManageOrder> fromOrderToManageOrder(List<OrderMaster> omlist);
	
	boolean saveOrderMasterResults(OrderSelection selectCode);

	boolean renewOrderMasterResults();
	
	List<ManageOrder> ambiguMemberNameSearch(String partMemberName, Integer sortWay, Map<String, Integer> limitOffset);
	
	Integer ambiguMemberNameSearchLength(String partMemberName);
	
	/**
	 * 
	 * @param condition(Map<String, Integer>): sql where key=value
	 * @return long: how many raws
	 */
	long countDataNum(Map<String, Integer> condition);
	
	OrderMaster getOne(Integer orderId);
	
	boolean updateFromManager(OrderMaster fromManager);
	
	boolean updateFromMember(OrderMaster fromMember);

	/**
	 * 
	 * @param memberId
	 * @param whereCondition(Map<String, Integer>) sql where conditon:value
	 * @param limitAndOffset(Map<String, Integer>) limit:value, offset:value
	 * @return List<MemberViewOrder>
	 */
	List<MemberViewOrder> memberOrderList(Map<String, Integer> whereCondition ,Map<String, Integer> limitAndOffset);

}
