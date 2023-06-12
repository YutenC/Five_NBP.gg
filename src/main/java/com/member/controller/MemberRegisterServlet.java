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
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.member.util.MemerCommonUitl.gsonToJson;


@WebServlet("/memberRegisterServlet")
public class MemberRegisterServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");      // 設定請求的編碼為UTF-8
        Member register = new Member();
        String account = request.getParameter("account");
        if(!account.equals("")){
            register.setAccount(account);
        }else{
            register = new Member();
            register.setMessage("帳號格式錯誤");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if(password.equals(confirmPassword)){
            register.setPassword(password);
        } else {
            register = new Member();
            register.setMessage("密碼與確認密碼不一致");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String nick = request.getParameter("nick");
        if(!nick.equals("")){
            register.setNick(nick);
        }else{
            register = new Member();
            register.setMessage("請輸入暱稱");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String idNumber = request.getParameter("id_number");
        if(!idNumber.equals("")){
            register.setId_number(idNumber);
        }else{
            register = new Member();
            register.setMessage("請輸入身分證字號");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String birthday = request.getParameter("birth");
        if(!birthday.equals("")){
            Date birth = Date.valueOf(birthday);
            register.setBirth(birth);
        }else{
            register = new Member();
            register.setMessage("請輸入生日");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String address = request.getParameter("address");
        if(!address.equals("")){
            register.setAddress(address);
        }else{
            register = new Member();
            register.setMessage("請輸入聯絡地址");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String phone = request.getParameter("phone");
        if(!phone.equals("")){
            register.setAddress(phone);
        }else{
            register = new Member();
            register.setMessage("請輸入聯絡電話");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }
        String email = request.getParameter("email");
        if(email.equals("")){
            register = new Member();
            register.setMessage("請輸入電子郵件");
            register.setSuccessful(false);
            gsonToJson(response,register);
            return;
        }

    }
}
