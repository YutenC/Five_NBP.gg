package com.member.dao.impl;

import com.member.dao.BankDao;
import com.member.entity.Bank;
import com.member.entity.Member;

import java.util.List;

public class BankDaoImpl implements BankDao {
    @Override
    public List<Bank> selectAll() {
        final String sql = "FROM Bank WHERE member_id = :member_id";
        return getSession().createQuery(sql, Bank.class).getResultList();
    }

    @Override
    public int insert(Bank bank) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Bank bank) {
        return 0;
    }

    @Override
    public Bank selectById(Integer id) {
        return null;
    }
}
