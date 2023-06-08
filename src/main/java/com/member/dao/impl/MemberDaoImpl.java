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

    private Session session = getSession();

    @Override
    public int insert(Member member) {
//        原本hibernate 的寫法(沒有用組態設定)
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();   // 從session來，開始交易
//        // 基本上上面3行是固定
//        session.persist(member);    // persist(填入要insert的物件)
//        transaction.commit();   //
//        return member.getMember_id();

//        hiberante-spring寫法
        session.beginTransaction();
        session.persist(member);
        session.getTransaction().commit();
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
//        原本hibernate 的寫法(沒有用組態設定)
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();

        // 不能直接用session.remove(id)，因為括弧內要放的想要刪除的物件
        // 所以要把刪除整筆資料時需要把整個想要刪除的Member物件放進來
        // 要"先查詢要刪除的會員id"，"再用該id指定給session，刪除該會員"
//        Member member = session.get(Member.class, id);
//        session.remove(member);
//        transaction.commit();
//        return member.getMember_id();

//        hibernate-spring寫法
//        Session session = getSession();
        Member member = session.load(Member.class, id);
        return 1;
    }


    @Override
    public int update(Member member) {  // 修改會員資料
//        原本hibernate 的寫法(沒有用組態設定)
//        return 0;
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Member SET ");

        final String password = member.getPassword();
        if (password != null && !password.isEmpty()) {
            hql.append("password = :password, ");
        }
        hql.append("nick = :nick, ")
                .append("email = :email, ")
                .append("phone = :phone, ")
                .append("birth = :birth, ")
                .append("id_number = :id_number, ")
                .append("address = :address, ")
                .append("bonus = :bonus, ")
                .append("member_ver_state = :member_ver_state, ")
                .append("suspend_deadline = :suspend_deadline, ")
                .append("headshot = :headshot, ")
                .append("ver_deadline = :ver_deadline, ")
                .append("violation = :violation ")
                .append("where account = :account");

        Query<?> query = session.createQuery(hql.toString());
        if (password != null && !password.isEmpty()) {
            query.setParameter("password", password);
        }

        return query
                .setParameter("nick", member.getNick())
                .setParameter("email", member.getEmail())
                .setParameter("phone", member.getPhone())
                .setParameter("birth", member.getBirth())
                .setParameter("id_number", member.getId_number())
                .setParameter("address", member.getAddress())
                .setParameter("bonus", member.getBonus())
                .setParameter("member_ver_state", member.getMember_ver_state())
                .setParameter("suspend_deadline", member.getSuspend_deadline())
                .setParameter("headshot", member.getHeadshot())
                .setParameter("ver_deadline", member.getVer_deadline())
                .setParameter("violation", member.getViolation())
                .setParameter("account", member.getAccount())
                .executeUpdate();
    }

    @Override
    public Member selectById(Integer id) {
//        Session session = getSession();

//        hibernate-spring寫法
//        return getSession().get(Member.class, id);
        return session.get(Member.class, id);
    }

    @Override
    public List<Member> selectAll() {
        final String hql = "FROM Member ORDER BY member_id";

        return session.createQuery(hql, Member.class).getResultList();
//        return null;
    }

    @Override
    public Member selectByUserName(String account) {
//        Session session = getSession();
        // 使用Criteria
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("account"),account));
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    public Member selectForLogin(String account, String password) {
        // 使用 Native SQL
        final String sql = "SELECT * FROM member WHERE account = :account and password = :password";

//        return getSession()
        return session
                .createNativeQuery(sql, Member.class)
                .setParameter("account", account)
                .setParameter("password", password)
                .uniqueResult();
    }
}
