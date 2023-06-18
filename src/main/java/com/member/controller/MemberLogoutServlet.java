package com.member.controller;

import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.MemerCommonUitl.getMemberSession;
import static com.member.util.MemerCommonUitl.gsonToJson;

@WebServlet("/memberLogoutServlet")
public class MemberLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getMemberSession(request,"member");
        System.out.println("會員：" + member.getNick() + " 成功登出");
        request.getSession().setAttribute("isLogin", false);
        request.getSession().removeAttribute("member");
        gsonToJson(response,member);
    }
}
