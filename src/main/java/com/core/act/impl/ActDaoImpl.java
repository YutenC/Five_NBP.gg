package com.core.act.dao;

import com.core.act.entity.Act;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ActDaoImpl implements ActDao {

    private final SessionFactory sessionFactory;

    public ActDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Act act) {
        getCurrentSession().persist(act);
    }

    @Override
    public void deleteByActId(int actId) {
        Act act = findByActId(actId);
        if (act != null) {
            getCurrentSession().delete(act);
        }
    }

    @Override
    public void update(Act act) {
        getCurrentSession().merge(act);
    }

    @Override
    public Act findByActId(int actId) {
        return getCurrentSession().get(Act.class, actId);
    }

    @Override
    public List<Act> findAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Act> query = builder.createQuery(Act.class);
        Root<Act> root = query.from(Act.class);
        query.select(root);
        return getCurrentSession().createQuery(query).getResultList();
    }

    /**
     * 獲取當前的 Hibernate Session
     * @return 當前的 Hibernate Session
     * Session即是SessionFactory的功能之一
     * 用於資料庫的交換即是新山改茶
     */
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
