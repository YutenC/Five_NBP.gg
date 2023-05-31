package com.member.service.impl;

import com.core.service.CoreService;
import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.entity.Member;
import com.member.service.MemberService;

import java.util.List;

public class MemberServiceimpl implements MemberService, CoreService {

    private MemberDao dao;

    public MemberServiceimpl(){
        dao = new MemberDaoImpl();
    }

    @Override
    public Member register(Member member) {     //註冊驗證
        if (member.getAccount() == null) {
            member.setMessage("帳號未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (member.getPassword() == null) {
            member.setMessage("密碼未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (member.getNick() == null) {
            member.setMessage("會員暱稱未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (member.getEmail() == null) {
            member.setMessage("會員信箱未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (member.getPhone() == null) {
            member.setMessage("連絡電話未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (member.getBirth() == null) {
            member.setMessage("生日未輸入");
            member.setSuccessful(false);
            return member;
        }

        return null;
    }

    @Override
    public Member login(Member member) {
        final String account = member.getAccount();
        final String password = member.getPassword();

        if (account == null) {
            member.setMessage("帳號未輸入");
            member.setSuccessful(false);
            return member;
        }

        if (password == null) {
            member.setMessage("密碼未輸入");
            member.setSuccessful(false);
            return member;
        }

        member = dao.selectForLogin(account, password);
        if (member == null) {
            member = new Member();
            member.setMessage("帳號或密碼錯誤");
            member.setSuccessful(false);
            return member;
        }

        member.setMessage("登入成功");
        member.setSuccessful(true);
        return member;
    }

    @Override
    public Member edit(Member member) {     // 會員自己編輯會員資料
        final Member oMember = dao.selectByUserName(member.getAccount());
        member.setEmail(oMember.getEmail());
        member.setPhone(oMember.getPhone());
        member.setId_number(oMember.getId_number());
        member.setAddress(oMember.getAddress());
        member.setHeadshot(oMember.getHeadshot());
        member.setMember_ver_state(oMember.getMember_ver_state());
        final int resultCount = dao.update(member);
        member.setSuccessful(resultCount > 0);
        member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
        return member;
    }

    @Override
    public List<Member> findAll() {
        return dao.selectAll();
    }

    @Override
    public boolean remove(Integer id) {
        return dao.deleteById(id) > 0;
    }

    @Override
    public boolean save(Member member) {
        return dao.update(member) > 0;
    }
}
