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

@WebServlet("/memberGetInforServlet")
public class MemberGetInforServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = getMemberSession(request,"member");
        if(member == null){
            Member visitor = new Member();
            visitor.setMessage("無會員資訊");
            visitor.setSuccessful(false);
            gsonToJson(response, visitor);
            return;
        }
        System.out.println("訊息：會員 " + member.getNick() + "取得資訊");
        gsonToJson(response, member);
    }
}
