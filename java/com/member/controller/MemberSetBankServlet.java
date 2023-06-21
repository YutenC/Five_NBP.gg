package com.member.controller;

import com.member.entity.Bank;
import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.member.util.MemberConstants.BANK_SERVICE;
import static com.member.util.MemerCommonUitl.getMemberSession;
import static com.member.util.MemerCommonUitl.gsonToJson;

@WebServlet("/memberSetBankServlet")
public class MemberSetBankServlet extends HttpServlet {
    private static final long serialVersionUID = -2575311513405024631L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = getMemberSession(request,"member");

        // action: 1->查詢全部帳戶 2->新增帳戶 3->刪除帳戶
        int action = Integer.parseInt(request.getParameter("action"));
        Bank bank = new Bank();
        bank.setMember_id(member.getMember_id());
        bank.setBank_name(request.getParameter("bank_name"));
        bank.setBank_number(request.getParameter("bank_number"));
        System.out.println(bank.getMember_id());
        System.out.println(bank.getBank_name());
        System.out.println(bank.getBank_number());

        switch (action){
            case 1:
                List<Bank> sbank = BANK_SERVICE.findAll();
                gsonToJson(response, sbank);
                break;
            case 2:
                BANK_SERVICE.add(bank);
                gsonToJson(response, bank);
                break;
            case 3:
                BANK_SERVICE.remove(bank.getBank_id());
                gsonToJson(response, bank);
                break;
        }
    }
}
