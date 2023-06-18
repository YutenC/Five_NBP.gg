package com.member.dao.impl;

import com.member.dao.FriendDao;
import com.member.entity.Friend;
import com.member.entity.Member;

import java.util.List;

public class FriendDaoImpl implements FriendDao {
    @Override
    public List<Friend> selectAll() {
        final String sql = "FROM Friend ORDER BY member_id";
        return getSession().createNativeQuery(sql, Friend.class).getResultList();
    }

    @Override
    public int insert(Friend friend) {
        getSession().persist(friend);
        return friend.getFriend_id();
    }

    @Override
    public int deleteById(Integer id) {
        Friend friend = getSession().get(Friend.class, id);
        getSession().remove(friend);
        friend.setMessage("刪除好友成功");
        return friend.getFriend_id();
    }

    @Override
    public int update(Friend friend) {
        return 0;
    }

    @Override
    public Friend selectById(Integer id) {
        return getSession().get(Friend.class, id);
    }

//    @Override
//    public Friend selectByNick(Member member){
//        final String sql = "";
//    }
}
