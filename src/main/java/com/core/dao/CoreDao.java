package com.core.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

import core.util.HibernateUtil;

public interface CoreDao<M, I> {

    int insert(M member);

    int deleteById(I id);

    int update(M member);

    M selectById(I id);

    List<M> selectAll();

    default Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
