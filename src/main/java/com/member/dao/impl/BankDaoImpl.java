package com.member.dao.impl;

import com.member.dao.BankDao;
import com.member.entity.Bank;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankDaoImpl implements BankDao {

    @Override
    public int deleteById(Integer id) {
        Bank bank = getSession().get(Bank.class, id);
        getSession().remove(bank);
//        getSession().getTransaction().commit();
        bank.setMessage("刪除成功");
        return bank.getBank_id();
    }

    @Override
    public List<Bank> selectAll() {
        final String hql = "FROM Bank ORDER BY member_id";
        return getSession().createQuery(hql, Bank.class).getResultList();
    }

    @Override
    public int insert(Bank bank) {
        getSession().persist(bank);
        return bank.getBank_id();
    }

    @Override
    public int update(Bank bank) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Bank SET ");
        final String bankNumber = bank.getBank_number();
        if(bankNumber != null && !bankNumber.isEmpty()){
            hql.append("bank_number = :obank_number, ");
        }
        hql.append("WHERE bank_number = :bank_number");

         Query<?> query = getSession().createQuery(hql.toString());
        if(bankNumber != null && !bankNumber.isEmpty()){
            query.setParameter("bank_number", bankNumber);
        }
        return query
                .setParameter("bank_number", bank.getBank_number())
                .executeUpdate();
    }

    @Override
    public Bank selectById(Integer id) {
        return getSession().get(Bank.class, id);
    }

    @Override
    public Bank selectByBankNumber(String bank_number){
        final String sql = "FROM Bank where bank_number = :bank_number";
        return getSession()
                .createNativeQuery(sql, Bank.class)
                .setParameter("bank_number", bank_number)
                .uniqueResult();
    }
}


