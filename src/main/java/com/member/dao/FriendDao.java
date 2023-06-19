package com.member.dao;

import com.core.dao.CoreDao;
import com.member.entity.Friend;
import com.member.entity.Member;

public interface FriendDao extends CoreDao<Friend, Integer> {

    int insert(Friend friend);

    int deleteById(Integer id);

    int update(Friend friend);

    Friend selectById(Integer id);

    Friend selectByNick(Member member);
}
