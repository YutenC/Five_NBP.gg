package com.core.dao;

import com.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public interface CoreDao<M, I> {

    int insert(M member);

    int deleteById(I id);

    int update(M member);

    M selectById(I id);

    List<M> selectAll();        // 查詢全部資料

    default Session getSession() {

//        return HibernateUtil.getSessionFactory().openSession();             // 單機測試
        return  HibernateUtil.getSessionFactory().getCurrentSession();    // 上線環境測試
    }
}
