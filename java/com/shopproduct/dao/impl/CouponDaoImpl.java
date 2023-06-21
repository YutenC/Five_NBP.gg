package com.shopproduct.dao.impl;


import com.shopproduct.core.CoreDaoImpl;
import com.shopproduct.dao.CouponDao;
import com.shopproduct.entity.Coupon;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


//extends BaseDAO<Coupon>
public class CouponDaoImpl extends CoreDaoImpl<Coupon, Integer> implements CouponDao {

    @Override
    public int update(Coupon coupon) {
        return 1;
    }


    @Override
    public Coupon selectByDiscountCode(String discountCode) {
        Session session = getSession();
//        Coupon coupon = session.get(Coupon.class, discountCode);
//        String hql = "from Coupon  where discountCode = "+discountCode;
//        System.out.println("hql: "+hql);
//        return getSession().createQuery(hql,Coupon.class).getSingleResult();


        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Coupon> criteriaQuery = criteriaBuilder.createQuery(Coupon.class);
        Root<Coupon> root = criteriaQuery.from(Coupon.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("discountCode"),discountCode));
        Query<Coupon> query = session.createQuery(criteriaQuery);
        query.setMaxResults(1);
        return query.getSingleResult();


    }
}
