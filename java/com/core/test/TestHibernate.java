package com.core.test;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.core.util.HibernateUtil;
import com.member.entity.Member;

public class TestHibernate {
	
	public static void main (String[] args) {
//		ProductDaoImpl pdl = new ProductDaoImpl();
//		
//		Product pd = new Product();
//		
//		pd.setProductName("新商品2");
//		pd.setType("22");
//		pd.setPrice(2000);
//		pd.setAmount(999);
//		pd.setBuyTimes(0);
//		pd.setBrand("PS");
//		pd.setRate(0);
//		pd.setRevieweCount(0);
//		pd.setContent("我是新商品喔2");
//		pd.setLaunchTime(java.sql.Date.valueOf("2023-06-05"));
//		
//		System.out.println(pdl.selectById(1));
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tx = session.beginTransaction();
		System.out.println(session.get(Member.class, 1));
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction().commit();
	}
}
