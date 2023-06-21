package com.member.dao.impl;

import com.member.dao.BlackListDao;
import com.member.entity.Black_list;

import java.util.List;

public class BlackListDaoImpl implements BlackListDao {
    @Override
    public List<Black_list> selectAll() {
        final String sql = "FROM Black_list ORDER BY member_id";
        return getSession().createNativeQuery(sql, Black_list.class).getResultList();
    }

    @Override
    public int insert(Black_list blackList) {
        getSession().persist(blackList);
        return blackList.getBlack_id();
    }

    @Override
    public int deleteById(Integer id) {
        Black_list blackList = getSession().get(Black_list.class, id);
        getSession().remove(blackList);
        blackList.setMessage("刪除好友成功");
        return blackList.getBlack_id();
    }

    @Override
    public int update(Black_list blackList) {
        return 0;
    }

    @Override
    public Black_list selectById(Integer id) {
        return getSession().get(Black_list.class, id);
    }
}

