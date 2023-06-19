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
    public Member forgetPassword(Member member);
}
