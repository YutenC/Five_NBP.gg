package com.member.service.impl;

import com.member.dao.CreditDao;
import com.member.dao.impl.CreditDapImpl;
import com.member.entity.Bank;
import com.member.entity.Member_credit;
import com.member.service.CreditService;

import java.util.List;

public class CreditServiceImpl implements CreditService {

    private CreditDao dao;

    public CreditServiceImpl() {
        dao = new CreditDapImpl();
    }

    @Override
    public Member_credit edit(Member_credit member_credit) {
        try {
            final Member_credit oMemberCredit = dao.selectByBankNumber(member_credit.getCred_number());
            if (member_credit.getBank_name() == null) {
                member_credit.setBank_name(oMemberCredit.getBank_name());
            } else {
                oMemberCredit.setBank_name(member_credit.getBank_name());
            }
            if (member_credit.getCred_number() == null) {
                member_credit.setCred_number(oMemberCredit.getCred_number());
            } else {
                oMemberCredit.setCred_number(member_credit.getCred_number());
            }
            oMemberCredit.setBank_name(member_credit.getBank_name());
            oMemberCredit.setCred_number(member_credit.getCred_number());
            final int resultCount = dao.update(member_credit);
            member_credit.setSuccessful(resultCount > 0);
            member_credit.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
            return member_credit;
        } catch (Exception e) {
            e.printStackTrace();
            member_credit.setMessage("修改失敗");
            return member_credit;
        }
    }

    @Override
    public Boolean remove(Integer id) {
        return dao.deleteById(id) > 0;
    }

    @Override
    public List<Member_credit> findAll() {
        return dao.selectAll();
    }

    @Override
    public Member_credit add(Member_credit member_credit) {
        return null;
    }
}
