package com.member.dao.impl;


import com.member.dao.MemberDao;
import com.member.entity.Member;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    @Override
    public int insert(Member member) {
        getSession().persist(member);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        Session session = getSession();
        Member member = session.get(Member.class,id);
        session.remove(member);
        return 1;
    }

    @Override
    public int update(Member member) {
        final StringBuilder hql = new StringBuilder();
        hql.append("update member set");
        int offset = 0;
        final String password = member.getPassword();
        if(password != null && !password.isEmpty()){
            hql.append("password = :password,");
            offset = 1;
        }
        hql.append("nick = :nick, email = :email");
        hql.append("email = :email");
        hql.append("phone = :phone");
        hql.append("birth = :birth");
        hql.append("id_number = :id_number");
        hql.append("address = :address");
        hql.append("bonus = :bonus");
        hql.append("member_ver_state = :member_ver_state");     // 會員認證狀態
        hql.append("suspend_deadline = :suspend_deadline");       // 停權期限
        hql.append("headshot = :headshot");
//        hql.append("ver_deadline = :ver_deadline");       // 會員認證期限(移到redis做)
        hql.append("violation = :violation");
        Query<?> query = getSession().createQuery(hql.toString());
        if(password != null && !password.isEmpty()){
            query.setParameter("password", query);
        }
        query.setParameter("nick", member.getNick());
        query.setParameter("email", member.getEmail());
        query.setParameter("phone", member.getPhone());
        query.setParameter("birth", member.getBirth());
        query.setParameter("id_number", member.getId_number());
        query.setParameter("address", member.getAddress());
        query.setParameter("bonus", member.getBonus());
        query.setParameter("member_ver_state", member.getMember_ver_state());
        query.setParameter("suspend_deadline", member.getSuspend_deadline());
        query.setParameter("headshot", member.getHeadshot());
        query.setParameter("violation", member.getViolation());
        return query.executeUpdate();

    }

    @Override
    public Member selectById(Integer id) {
        return getSession().get(Member.class, id);
    }

    @Override
    public List<Member> selectAll() {
        final String hql = "FROM Member ORDER BY id";
        Session session = getSession();
        Query<Member> memberQuery = session.createQuery(hql, Member.class);
        return memberQuery.getResultList();
    }

    @Override
    public Member selectByUserName(String account) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("account"), account));
        Query<Member> memberQuery = session.createQuery(criteriaQuery);
        return memberQuery.uniqueResult();
    }

    @Override
    public Member selectForLogin(String account, String password) {
        final String hql = "select * from member where account = :account and password = :password";
        Session session = getSession();
        NativeQuery<Member> nativeQuery = session.createNativeQuery(hql, Member.class);
        nativeQuery.setParameter("account", account);
        nativeQuery.setParameter("password", password);
        return nativeQuery.uniqueResult();
    }
}
