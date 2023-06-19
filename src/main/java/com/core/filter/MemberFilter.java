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

@WebFilter("/member/*")
public class MemberFilter extends HttpFilter implements Filter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Member member = new Member();
        Object isLogin =  request.getSession().getAttribute("isLogin");
        System.out.println(isLogin);
        String uri = request.getRequestURI();   // 取得目前的位置

        if(isLogin != null ){
            chain.doFilter(request,response);
        } else {
            response.sendRedirect("/Five_NBP_gg/member_login.html");
        }
    }
}
