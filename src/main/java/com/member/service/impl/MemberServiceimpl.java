package com.member.service.impl;

import com.core.service.CoreService;
import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.entity.Member;
import com.member.service.MemberService;
import com.mysql.cj.Query;

import javax.transaction.Transactional;
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
        try{
//            建立HibernateFilter後交易機制交給他處理，beginTransaction, commit, rollback都可以註解掉
//            beginTransaction();
            if (dao.selectByUserName(member.getAccount())!= null) {
                member.setMessage("帳號重複");
                member.setSuccessful(false);
//                rollback();
                return member;
            }


            final int resultCount = dao.insert(member);
            if (resultCount < 1) {
                member.setMessage("註冊錯誤，請聯絡管理員!");
                member.setSuccessful(false);
//                rollback();
                return member;
            }
//            commit();
        }catch (Exception e){
//            rollback();
            e.printStackTrace();
        }
//        System.out.println(1);
        return member;          // 不確定retrun什麼
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
        member.setPassword(oMember.getPassword());
        member.setNick(oMember.getNick());
        member.setEmail(oMember.getEmail());
        member.setPhone(oMember.getPhone());
        member.setBirth(oMember.getBirth());
        member.setId_number(oMember.getId_number());
        member.setAddress(oMember.getAddress());
        member.setBonus(oMember.getBonus());
        member.setMember_ver_state(oMember.getMember_ver_state());
        member.setSuspend_deadline(oMember.getSuspend_deadline());
        member.setHeadshot(oMember.getHeadshot());
        member.setVer_deadline(oMember.getVer_deadline());
        member.setViolation(oMember.getViolation());
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
        // 原始寫法
//        return dao.deleteById(id) > 0;
//        try{
//        建立HibernateFilter後交易機制交給他處理，beginTransaction, commit, rollback都可以註解掉
//        回傳dao.deleteById(id) > 0 即可(回傳 >0原因 如下)
//            beginTransaction();
//            final int resultCount = dao.deleteById(id);
//            commit();
//            return resultCount > 0;
//        }catch (Exception e){
//            rollback();
//            e.printStackTrace();
//            return false;
//        }
        return dao.deleteById(id) > 0;
    }

    @Override
    public boolean save(Member member) {
        return dao.update(member) > 0;
    }
}
