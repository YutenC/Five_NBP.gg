package com.shop.shopproduct.core;

import com.shop.shopproduct.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public interface CoreDao<P, I> {

    int insert(P pojo);

    int deleteById(I id);

    int update(P pojo);

    P selectById(I id);

    List<P> selectAll();

    default Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
//        return HibernateUtil.getSessionFactory().openSession();
    }


//    Class<?> entityClass = null;
//
//
//
//    default int insert(P pojo) {
//        getSession().persist(pojo);
//        return 0;
//    }
//
//    default int deleteById(I id) {
//        Session session = getSession();
//        Object coupon = session.get(entityClass, (Serializable) id);
//        session.remove(coupon);
//        return 0;
//    }
//
//    default int update(P pojo) {
//        return 0;
//    }
//
//    default P selectById(I id) {
//        Session session = getSession();
//        P obj = (P) session.get(entityClass, (Serializable) id);
//        return obj;
//    }
//
//    List<P> selectAll();
//
//    default Session getSession() {
//        return HibernateUtil.getSessionFactory().getCurrentSession();
////        return HibernateUtil.getSessionFactory().openSession();
//    }
}

