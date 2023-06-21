package com.member.controller;

import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.JedisCommonUtil.getCodes;
import static com.member.util.MemberConstants.SERVICE;
import static com.member.util.MemerCommonUitl.*;

@WebServlet("/memberVertifyServlet")
public class MemberVertifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member register = new Member();
        String email = request.getParameter("email");
        String verfiyCode = request.getParameter("verification_code");     // 填入的驗證碼

        register.setEmail(email);
        Member member = getCodes(register, verfiyCode);
        Boolean isPass =  member.isSuccessful();
        if(isPass){
            Member passRegister = getMemberSession(request,"register");   // 從session中取得註冊會員資訊
            if(passRegister != null){
                passRegister.setMember_ver_state(1);
                SERVICE.edit(passRegister);       // 修改會員驗證狀態
                request.getSession().removeAttribute("register");   // 移除seesion中會員註冊資訊
                passRegister.setMessage("驗證成功");
                gsonToJson(response, passRegister);
            }
            Member passMember = getMemberSession(request,"member");
            if(passMember != null){
                passMember.setMember_ver_state(1);
                SERVICE.edit(passMember);
                passMember.setMessage("驗證成功");
                gsonToJson(response, visitorData(passMember));
            }
        } else {
            gsonToJson(response, member);
        }
    }
}
