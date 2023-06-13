package com.core.listener;

import com.core.util.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateListener implements ServletContextListener {

//  利用ServletContextLinster來監聽控制SessionFactory
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.shutdown();
    }
}

