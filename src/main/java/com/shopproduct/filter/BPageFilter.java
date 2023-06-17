package com.shopproduct.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class BPageFilter extends HttpFilter {
//    String path = "/WEB-INF/pages";
    String path = "/html";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {

        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        System.out.println("contextPath: " + contextPath);
        System.out.println("servletPath: " + servletPath);


        try {
            if (servletPath.endsWith(".html") ) {//|| servletPath.endsWith(".css")
                req.getRequestDispatcher(path + servletPath).forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }
}
