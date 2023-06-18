package com.member.dao;

import com.core.dao.CoreDao;
import com.member.entity.Bank;

public interface BankDao extends CoreDao<Bank, Integer> {

    int insert(Bank bank);

    int deleteById(Integer id);

    int update(Bank bank);

    Bank selectById(Integer id);

    Bank selectByBankNumber(String bank_number);

}
