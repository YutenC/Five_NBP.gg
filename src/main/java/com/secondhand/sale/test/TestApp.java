package com.secondhand.sale.test;

import com.core.util.HibernateUtil;
import com.secondhand.sale.entity.SecondhandProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class TestApp {

    public static void main(String[] args) {
        SecondhandProduct sp = new SecondhandProduct();
        sp.setName("PS手把");
        sp.setType("01");
        sp.setPrice(2000);
        sp.setContent("abcdefghijklmn");
        sp.setLaunchTime(new Date());


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        SecondhandProduct secondhandProduct = session.get(SecondhandProduct.class, 1);
        System.out.println(secondhandProduct.getContent());
    }


}
