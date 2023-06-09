package com.member.controller;

import com.manager.service.impl.ManagerServiceImpl;
import com.member.entity.Member;
import com.member.service.impl.MemberServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/html/member_register")
public class MemberRegisterServlet extends HttpServlet {

    private static  final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");      // 設定請求的編碼為UTF-8
        Member member = new Member();
        MemberServiceimpl memberServiceimpl = new MemberServiceimpl();

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String id_number = request.getParameter("id_number");
        String birth = request.getParameter("birth");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("account");

        System.out.println(account);
    }


}
