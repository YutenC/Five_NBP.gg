package com.member.dao.impl;

//import static core.util.CommonUtil.getConnection;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;


import com.core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.member.dao.MemberDao;
import com.member.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

//    Session session = getSession();

    @Override
    public int insert(Member member) {

//        Transaction transaction = session.beginTransaction();   // 從session來，開始交易
//        // 基本上上面3行是固定
        getSession().persist(member);    // persist(填入要insert的物件)
//        transaction.commit();   //
        return member.getMember_id();
    }

    @Override
    public int deleteById(Integer id) {
//        getSession().beginTransaction();
        // 不能直接用session.remove(id)，因為括弧內要放的想要刪除的物件
        // 所以要把刪除整筆資料時需要把整個想要刪除的Member物件放進來
        // 要"先查詢要刪除的會員id"，"再用該id指定給session，刪除該會員"
        Member member = getSession().get(Member.class, id);
        getSession().remove(member);
//        getSession().getTransaction().commit();
        member.setMessage("刪除成功");
        return member.getMember_id();
    }


    @Override
    public int update(Member member) {  // 修改會員資料

        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Member SET ");

        final String password = member.getPassword();
        if (password != null && !password.isEmpty()) {
            hql.append("password = :password, ");
        }
        hql.append("email = :email, ")
                .append("phone = :phone, ")
                .append("address = :address, ")
                .append("bonus = :bonus, ")
                .append("member_ver_state = :member_ver_state, ")
                .append("headshot = :headshot, ")
                .append("violation = :violation ")
                .append("where account = :account");

        Query<?> query = getSession().createQuery(hql.toString());
        if (password != null && !password.isEmpty()) {
            query.setParameter("password", password);
        }

        return query
                .setParameter("email", member.getEmail())
                .setParameter("phone", member.getPhone())
                .setParameter("address", member.getAddress())
                .setParameter("bonus", member.getBonus())
                .setParameter("member_ver_state", member.getMember_ver_state())
                .setParameter("headshot", member.getHeadshot())
                .setParameter("violation", member.getViolation())
                .setParameter("account", member.getAccount())
                .executeUpdate();
    }

    @Override
    public Member selectById(Integer id) {
        return getSession().get(Member.class, id);
    }

    @Override
    public List<Member> selectAll() {
        // 老師寫法
        final String hql = "FROM Member ORDER BY member_id";
        return getSession().createQuery(hql, Member.class).getResultList();
    }

    @Override
    public Member selectByAccount(String account) {
//          老師的寫法
//        Session session = getSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//        Root<Member> root = criteriaQuery.from(Member.class);
//        criteriaQuery.where(criteriaBuilder.equal(root.get("account"),account));
//        return session.createQuery(criteriaQuery).uniqueResult();

        final String sql = "SELECT * FROM member WHERE account = :account";
        return getSession()
                .createNativeQuery(sql, Member.class)
                .setParameter("account", account)
                .uniqueResult();
    }

    @Override
    public Member selectForLogin(String account, String password) {
        // 使用 Native SQL
        final String sql = "SELECT * FROM member WHERE account = :account and password = :password";
        return getSession()
                .createNativeQuery(sql, Member.class)
                .setParameter("account", account)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public Member selectByEmail(String email) {
        final String sql = "SELECT * FROM member WHERE email = :email";
        return getSession()
                .createNativeQuery(sql, Member.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public Member selectByAccountNEmail(String account, String email){
        final String sql = "SELECT * FROM member WHERE account = :account and email = :email";
        return getSession()
                .createNativeQuery(sql, Member.class)
                .setParameter("account", account)
                .setParameter("email", email)
                .uniqueResult();
    }
}
