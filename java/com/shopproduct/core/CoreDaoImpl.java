package com.shopproduct.core;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class CoreDaoImpl<P, I> implements CoreDao <P, I>{
    private Class<?> entityClass;
    private String entityName;
    public CoreDaoImpl(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        try {
            entityClass = Class.forName(types[0].getTypeName());
            entityName=types[0].getTypeName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(P pojo) {
        Session session = getSession();
//        Transaction transaction= session.beginTransaction();
//        session.persist(pojo);


//        transaction.commit();
        return (int)session.save(pojo);
    }

    @Override
    public int deleteById(I id) {
        Session session = getSession();
        Object coupon = session.get(entityClass, (Serializable) id);
        session.remove(coupon);
        return 0;
    }

    @Override
    public int update(P pojo) {
        return 0;
    }

    @Override
    public P selectById(I id) {
        Session session = getSession();
        P coupon = (P) session.get(entityClass, (Serializable)id);
        return coupon;
    }

    @Override
    public List<P> selectAll() {
        String hql = "from "+entityName;
        return (List<P> )getSession().createQuery(hql,entityClass).getResultList();
    }
}
