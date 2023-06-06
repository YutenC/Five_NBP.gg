package com.member.dao.impl;

//import static core.util.CommonUtil.getConnection;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.member.dao.MemberDao;
import com.member.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private Session session;

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

        session.persist(member);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
//        原本hibernate 的寫法(沒有用組態設定)
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        // 不能直接用session.remove(id)，因為括弧內要放的想要刪除的物件
//        // 所以要把刪除整筆資料時需要把整個想要刪除的Member物件放進來
//        // 要"先查詢要刪除的會員id"，"再用該id指定給session，刪除該會員"
//        Member member = session.get(Member.class, id);
//        session.remove(member);
//        transaction.commit();
//        return member.getMember_id();
        Member member = session.get(Member.class, id);
        return 1;
    }

    @Override
    public int update(Member member) {  // 修改會員資料
//        原本hibernate 的寫法(沒有用組態設定)
//        return 0;
        final StringBuilder hql = new StringBuilder()
                .append("update member set");

        final String password = member.getPassword();
        if (password != null && !password.isEmpty()) {
            hql.append("password =: password");
        }
        hql.append("password = :password")
                .append("nick = :nick")
                .append("email = :email")
                .append("phone = :phone")
                .append("address = :address")
                .append("member_ver_state = :member_ver_state")
                .append("headshot = :headshot")
                .append("violation = :violation");

        Query<?> query = session.createQuery(hql.toString());
        if (password != null && !password.isEmpty()) {
            query.setParameter("password", password);
        }

        return query
                .setParameter("nick", member.getNick())
                .setParameter("email", member.getEmail())
                .setParameter("phone", member.getPhone())
                .setParameter("address", member.getAddress())
                .setParameter("member_ver_state", member.getMember_ver_state())
                .setParameter("headshot", member.getHeadshot())
                .setParameter("violation", member.getViolation())
                .executeUpdate();
    }

    @Override
    public Member selectById(Integer id) {
//        Session session = getSession();
        return getSession().get(Member.class, id);
    }

    @Override
    public List<Member> selectAll() {
        final String hql = "FROM Member ORDER BY member_id";

        return session.createQuery(hql, Member.class).getResultList();
//        return null;
    }

    @Override
    public Member selectByUserName(String account) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("account"),account));

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    public Member selectForLogin(String account, String password) {
        final String sql = "SELECT * FROM five.member WHERE account = :account and password =: password";

        return session
                .createNativeQuery(sql, Member.class)
                .setParameter("account", account)
                .setParameter("password", password)
                .uniqueResult();
    }

    public static void main(String[] args) {
        MemberDaoImpl dao = new MemberDaoImpl();

        Member member = dao.selectById(101);

        System.out.println(member);
    }
}
