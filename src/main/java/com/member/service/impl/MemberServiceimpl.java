package com.member.service.impl;

import com.core.service.CoreService;
import com.member.dao.MemberDao;
import com.member.dao.impl.MemberDaoImpl;
import com.member.entity.Member;
import com.member.service.MemberService;
import org.hibernate.Transaction;

import java.util.List;

public class MemberServiceimpl implements MemberService, CoreService {

    private MemberDao dao;

    public MemberServiceimpl() {
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
        try {
            beginTransaction();
            if (dao.selectByAccount(member.getAccount()) != null) {
                member.setMessage("帳號重複");
                member.setSuccessful(false);
                rollback();
                return member;
            }

            if (dao.selectByEmail(member.getEmail()) != null) {
                member.setMessage("信箱重複");
                member.setSuccessful(false);
                rollback();
                return member;
            }

            final int resultCount = dao.insert(member);
            if (resultCount < 1) {
                member.setMessage("註冊錯誤，請聯絡管理員!");
                member.setSuccessful(false);
                rollback();
                return member;
            }
            commit();
            member.setMessage("註冊成功");
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            member.setMessage("註冊失敗");
        }
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
//        --因為查詢登入是需要交易，所以開始交易寫在這--
//        beginTransaction();
        member = dao.selectForLogin(account, password);
        if (member == null) {
            member = new Member();
            member.setMessage("帳號或密碼錯誤");
            member.setSuccessful(false);
            return member;
        }
        //        --以上為驗證機制--
//        commit();
        member.setMessage("登入成功");
        member.setSuccessful(true);
        return member;
    }


    @Override
    public Member edit(Member member) {     // 會員自己編輯會員資料(暱稱、email、電話、大頭照)
        try {
            beginTransaction();
            final Member oMember = dao.selectByAccount(member.getAccount());
            // oMember 為資料庫原始資料     member 為會員輸入的資料
            if (member.getEmail() == null) {
                member.setEmail(oMember.getEmail());
            } else {
                oMember.setEmail(member.getEmail());
            }
            if (member.getPhone() == null) {
                member.setPhone(oMember.getPhone());
            } else {
                oMember.setPhone(oMember.getPhone());
            }
            if (member.getAddress() == null) {
                member.setAddress(oMember.getAddress());
            } else {
                oMember.setAddress(oMember.getAddress());
            }
            oMember.setAccount(member.getAccount());
            oMember.setPassword(member.getPassword());
            final int resultCount = dao.update(member);
            commit();
            member.setSuccessful(resultCount > 0);
            member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
            return member;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            member.setMessage("修改失敗");
            return member;
        }
    }

    @Override
    public List<Member> findAll() {
        beginTransaction();
        List<Member> memberList = dao.selectAll();
        commit();
        return memberList;
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
    public boolean setPassword(Member member) {
        return false;
    }

    @Override
    public String resetPassword(Member member) {
        return null;
    }

    @Override
    public Member setHeadshot(Member member) {
        return null;
    }

    @Override
    public boolean setMemberStatus(Member member) {
        beginTransaction();
        try {
            final Member verMember = dao.selectByEmail(member.getEmail());
            verMember.setMember_ver_state(member.getMember_ver_state());
            dao.update(verMember);
            System.out.println("驗證成功");
            commit();
            return true;
        } catch (Exception e) {
            System.out.println("驗證失敗");
            e.printStackTrace();
            return false;
        }
    }


}
