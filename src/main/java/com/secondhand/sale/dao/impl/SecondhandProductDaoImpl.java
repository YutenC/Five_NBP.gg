package com.secondhand.sale.dao.impl;


import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.entity.SecondhandProduct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class SecondhandProductDaoImpl implements SecondhandProductDao {


//    private Session session = getSession();
//    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//    Session session = sessionFactory.openSession();  //測試可以寫，但如果是web上線就不要寫，危險且使用者是共用同一條session

    @Override
    public int insert(SecondhandProduct secondhandproduct) {
        Session session = getSession();
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();  // 有用spring就可以省略
        session.persist(secondhandproduct);
        transaction.commit();
        return 1;
    }

    @Override
    public int deleteById(Integer productId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        SecondhandProduct secondhandproduct = session.load(SecondhandProduct.class, productId);
        session.remove(secondhandproduct);
        transaction.commit();
        return 1;
    }


    public int update(SecondhandProduct secondhandproduct) {
        Session session = getSession();
        session.beginTransaction();
        session.update(secondhandproduct);
        session.getTransaction().commit();
        return 1;
    }

    @Override
    public SecondhandProduct selectById(Integer productId) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        SecondhandProduct result = session.get(SecondhandProduct.class, productId);
        transaction.commit();
        return result;
    }

    @Override
    public List<SecondhandProduct> selectAll() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        final String hql = "FROM SecondhandProduct ORDER BY productId";
        List<SecondhandProduct> result = session.createQuery(hql, SecondhandProduct.class).getResultList();
        transaction.commit();
        return result;
    }

    @Override
    public List<SecondhandProduct> selectByTime() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        final String hql = "FROM SecondhandProduct ORDER BY launchTime";
        List<SecondhandProduct> result = session.createQuery(hql, SecondhandProduct.class).getResultList();
        transaction.commit();
        return result;
    }


}
