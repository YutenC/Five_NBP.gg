package com.secondhand.sale.dao.impl;


import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.entity.SecondhandProduct;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SecondhandProductDaoImpl implements SecondhandProductDao {


    @PersistenceContext
    private Session session;


    @Override
    public int insert(SecondhandProduct secondhandproduct) {
        getSession().persist(secondhandproduct);
        return 1;
    }

    @Override
    public int deleteById(Integer productId) {
        Session session = getSession();
        SecondhandProduct secondhandproduct = session.load(SecondhandProduct.class, productId);
        session.remove(secondhandproduct);
        return 1;
    }


    public int update(SecondhandProduct secondhandproduct) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE SecondhandProduct SET");
        final String productId = String.valueOf(secondhandproduct.getProductId());
        if (productId != null && !productId.isEmpty()) {
            hql.append("product_id = :product_id,");
        }
        hql.append("name = :name,")
                .append("type = :type,")
                .append("price = :price,")
                .append("content = :content,")
                .append("launch_time = launch_time ");


        Query query = getSession().createQuery(hql.toString());
        if (productId != null && !productId.isEmpty()) {
            query.setParameter("product_id", secondhandproduct.getProductId());
        }
        return query.setParameter("name", secondhandproduct.getName())
                .setParameter("type", secondhandproduct.getType())
                .setParameter("price", secondhandproduct.getPrice())
                .setParameter("content", secondhandproduct.getContent())
                .setParameter("launch_time", secondhandproduct.getLaunchTime())
                .executeUpdate();
    }

    @Override
    public SecondhandProduct selectById(Integer productId) {
        return session.get(SecondhandProduct.class, productId);
    }

    @Override
    public List<SecondhandProduct> selectAll() {
        final String hql = "FROM SecondhandProduct ORDER BY productId";
        return session.createQuery(hql, SecondhandProduct.class).getResultList();
//        return null;
    }




}
