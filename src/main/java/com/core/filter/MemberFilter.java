package com.core.filter;

import com.member.entity.Member;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.MemerCommonUitl.getMemberSession;

//@WebFilter("/member/*")
public class MemberFilter extends HttpFilter implements Filter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Member member = new Member();
        member.setSuccessful((Boolean) request.getSession().getAttribute("isLogin"));



    }
}
