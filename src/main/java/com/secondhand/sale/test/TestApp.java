package com.secondhand.sale.test;

import com.core.filter.HibernateFilter;
import com.core.util.HibernateUtil;
import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.dao.impl.SecondhandProductDaoImpl;
import com.secondhand.sale.entity.SecondhandProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();

// ===========新增
        SecondhandProduct sp = new SecondhandProduct();
        sp.setName("PlayStationg.手把");
        sp.setType("02");
        sp.setPrice(500);
        sp.setContent("ccccccc");
        sp.setLaunchTime(new Date());

        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
        spdao.insert(sp);


// ===========刪除
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
//        spdao.deleteById(1);


// ===========修改
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
//        SecondhandProduct newsp = spdao.getSession().get(SecondhandProduct.class, 2);
//        newsp.setContent("TESTnewcontentttttt");
//        newsp.setLaunchTime(new Date());
//        spdao.update(newsp);

// ===========selectById
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
//        SecondhandProduct spinfo = spdao.selectById(2);
//        System.out.println(spinfo.getName());


// ===========selectAll
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
//        List<SecondhandProduct> allsp = spdao.selectAll();
//        if (!(allsp.isEmpty())) {
//            for (SecondhandProduct sp : allsp) {
//                System.out.println(sp.getProductId());
//                System.out.println(sp.getName());
//            }
//        }else{
//            System.out.println("未找到任何商品");
//        }

// ===========selectByTime
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
//        List<SecondhandProduct> spLaunchTime = spdao.selectByTime();
//        if (!(spLaunchTime.isEmpty())) {
//            for (SecondhandProduct sp : spLaunchTime) {
//                System.out.println(sp.getProductId());
//                System.out.println(sp.getName());
//                System.out.println(sp.getLaunchTime());
//            }
//        }else{
//            System.out.println("fail");
//        }



        // ===========其它測試
//        Transaction trans = session.beginTransaction();
//        trans.commit();

//        System.out.println(spdao.selectAll());

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        SecondhandProductDao spdao = new SecondhandProductDaoImpl();
        //        Transaction trans = session.beginTransaction();
//        spdao.getSession();
//        spdao.insert(sp);

//        System.out.println(spdao.selectAll());

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction trans = session.beginTransaction();
//        session.persist(sp);
//        trans.commit();
//        System.out.println(session.get(SecondhandProduct.class, 2).getContent());
    }


}
