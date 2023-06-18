package com.member.service;

import com.core.service.CoreService;
import com.member.entity.Member;

import java.util.List;

public interface MemberService extends CoreService {

    Member register(Member member);     //會員註冊
    Member login(Member member);        //會員登入
    Member edit(Member member);         //會員編輯會員資料(只能編輯暱稱、email、電話、大頭照、地址)
    List<Member> findAll();             //尋找所有會員
    boolean remove(Integer id);         //移除會員
    boolean setPassword(Member member);       //會員重置密碼
    String resetPassword(Member member);      //會員忘記密碼重新寄發隨機密碼
    boolean setMemberStatus(Member member);      //會員狀態驗證
    Member setHeadshot(Member member);        //會員設定大頭照


}
