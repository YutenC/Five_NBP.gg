package com.member.dao.impl;

import com.member.dao.CreditDao;
import com.member.entity.Member_credit;
import org.hibernate.query.Query;

import java.util.List;

public class CreditDapImpl implements CreditDao {
    @Override
    public List<Member_credit> selectAll() {
        final String hql = "FROM Member_credit ORDER BY member_id";
        return getSession().createQuery(hql, Member_credit.class).getResultList();
    }

    @Override
    public int insert(Member_credit member_credit) {
        getSession().persist(member_credit);
        return member_credit.getCredit_id();
    }

    @Override
    public int deleteById(Integer id) {
        Member_credit member_credit = getSession().get(Member_credit.class, id);
        getSession().remove(member_credit);
//        getSession().getTransaction().commit();
        member_credit.setMessage("刪除成功");
        return member_credit.getCredit_id();
    }

    @Override
    public int update(Member_credit member_credit) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Member_credit SET ");
        final String creditNumber = member_credit.getCred_number();
        if(creditNumber != null && !creditNumber.isEmpty()){
            hql.append("cred_number = :ocred_number, ");
        }
        hql.append("WHERE cred_number = :cred_number");

        Query<?> query = getSession().createQuery(hql.toString());
        if(creditNumber != null && !creditNumber.isEmpty()){
            query.setParameter("cred_number", creditNumber);
        }
        return query
                .setParameter("cred_number", member_credit.getCred_number())
                .executeUpdate();
    }

    @Override
    public Member_credit selectById(Integer id) {
        return getSession().get(Member_credit.class, id);
    }

    @Override
    public Member_credit selectByBankNumber(String cred_number) {
        final String sql = "FROM Member_credit where cred_number = :cred_number";
        return getSession()
                .createNativeQuery(sql, Member_credit.class)
                .setParameter("cred_number", cred_number)
                .uniqueResult();
    }
}
