package com.member.controller;

import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.member.util.MemberConstants.SERVICE;
import static com.member.util.MemerCommonUitl.*;

@WebServlet("/memberSetPasswordServlet")
public class MemberSetPasswordServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
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

        String originPassword = member.getPassword();
        String password = request.getParameter("originPassword");
        String setPassword = request.getParameter("setPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!originPassword.equals(password)){
            Member visitor = new Member();
            visitor.setMessage("與原密碼不一致");
            visitor.setSuccessful(false);
            gsonToJson(response, visitor);
            return;
        }
        if(!setPassword.equals(confirmPassword)){
            Member visitor = new Member();
            visitor.setMessage("修改密碼不一致");
            visitor.setSuccessful(false);
            gsonToJson(response, visitor);
            return;
        }
        member.setPassword(setPassword);
        member = SERVICE.edit(member);
        System.out.println("訊息：會員 " + member.getNick() + " 重設密碼");

        Member visitor = visitorData(member);
        gsonToJson(response,visitor);
    }
}
