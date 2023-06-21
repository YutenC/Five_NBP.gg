package com.shopproduct.filter;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shopproduct.core.util.HibernateUtil;


@WebFilter("/*")
public class HibernateFilterShopProduct extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//        String contextPath=req.getContextPath();
//        String servletPath=req.getServletPath();
//        System.out.println("contextPath: "+contextPath);
//        System.out.println("servletPath: "+servletPath);

//        if(servletPath.endsWith(".html")||servletPath.endsWith(".css")||servletPath.endsWith(".js")){
//
//        }

        try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            chain.doFilter(req, res);
            transaction.commit();
//            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

<<<<<<< HEAD:src/main/java/com/shopproduct/filter/HibernateFilterShopProduct.java
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            RedisFactory.getRedisServiceInstance().process();
            RedisFactory.clear();
            session.getTransaction().rollback();
=======
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            transaction = session.beginTransaction();
//            RedisFactory.getRedisServiceInstance().process();
//            RedisFactory.clear();
//            transaction.rollback();
>>>>>>> kazuPende:java/com/shopproduct/filter/HibernateFilterShopProduct.java
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        }
    }
}
