package com.member.dao.impl;

import com.member.dao.NoticeDao;
import com.member.entity.Notice;

import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    @Override
    public int update(Notice member) {
        return 0;
    }

    @Override
    public Notice selectById(Integer id) {
        return getSession().get(Notice.class, id);
    }

    @Override
    public List<Notice> selectAll() {
        final String hql = "FROM Notice ORDER BY member_id";
        return getSession().createQuery(hql, Notice.class).getResultList();
    }

    @Override
    public int insert(Notice notice) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int delectByMember(Integer id) {
        return 0;
    }
}
