package com.member.dao;

import com.core.dao.CoreDao;
import com.member.entity.Bank;
import com.member.entity.Member_credit;

public interface CreditDao extends CoreDao<Member_credit, Integer> {

    int insert(Member_credit member_credit);

    int deleteById(Integer id);

    int update(Member_credit member_credit);

    Member_credit selectById(Integer id);

    Member_credit selectByBankNumber(String cred_number);
}
