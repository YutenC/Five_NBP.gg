package com.member.controller;

import com.google.gson.Gson;
import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.core.util.CommonUtil.*;
import static com.member.util.MemberConstants.SERVICE;
import static com.member.util.MemerCommonUitl.gsonToJson;
import static com.member.util.MemerCommonUitl.visitorData;

@WebServlet("/memberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");      // 設定輸入文字的編碼
        Member member = new Member();
        member.setAccount(request.getParameter("account").trim());
        member.setPassword(request.getParameter("password").trim());
        if ((member.getAccount().equals("")) || (member.getPassword().equals(""))) {
            member = new Member();
            member.setMessage("請填寫帳號密碼");
            member.setSuccessful(false);
            gsonToJson(response,member);
            return;
        }

        member = SERVICE.login(member);
        System.out.println("訊息：會員 " + member.getNick() + " " + member.getMessage());
        //  是否登入成功的訊息

        if (member.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            final HttpSession session = request.getSession();
            session.setAttribute("isLogin", true);
            session.setAttribute("member", member);
        }

        Member visitor = visitorData(member);
        gsonToJson(response, visitor);
    }
}
