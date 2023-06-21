package com.member.controller;

import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.JedisCommonUtil.saveCodes;
import static com.member.util.MemberConstants.SENDMAIL;
import static com.member.util.MemerCommonUitl.*;
import static com.member.util.MemerCommonUitl.verificationCode;

@WebServlet("/memberSendVertifyServlet")
public class MemberSendVertifyServlet extends HttpServlet {

        private static final long serialVersionUID = 6278567220887318688L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Member member = new Member();
        member.setEmail(request.getParameter("email"));
        String verificationCode = verificationCode();
        String title = "NBP.gg 會員驗證信";
        String messageText = "您的驗證密碼為：" + verificationCode + " 請於30秒內完成會員驗證";
        SENDMAIL.mailService(member, title, messageText);

        saveCodes(member, verificationCode);        // 將驗證碼存入Redis中，驗證碼存活30s，需要用信箱比對

        member.setMessage("驗證碼已寄出");
        gsonToJson(response,member);
    }
}
