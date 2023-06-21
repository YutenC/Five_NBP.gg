package com.member.controller;

import com.core.service.MailService;
import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.MemberConstants.SENDMAIL;
import static com.member.util.MemberConstants.SERVICE;
import static com.member.util.MemerCommonUitl.*;

@WebServlet("/memberForgetServlet")
public class MemberForgetServlet extends HttpServlet {
    private static  final long serialVersionUID = 5152388072271321070L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = new Member();
        member.setAccount(request.getParameter("account"));
        member.setEmail(request.getParameter("email"));

        Member fMember = SERVICE.forgetPassword(member);
        if(fMember.isSuccessful()){
            String title = "NBP.gg 密碼重置信";
            String randomPassword = verificationCode();
            String messageContext = "您重製的密碼是：" + randomPassword + " ,請登入後重置密碼。";
            SENDMAIL.mailService(fMember, title, messageContext);
            fMember.setPassword(randomPassword);
            SERVICE.edit(fMember);

            gsonToJson(response, visitorData(fMember));
        } else {
            gsonToJson(response, visitorData(fMember));
        }
    }
}
