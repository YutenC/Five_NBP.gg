package com.shop.shopproduct.dao.impl;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.shop.shopproduct.core.CoreDaoImpl;
import com.shop.shopproduct.dao.CouponDao;
import com.shop.shopproduct.entity.Coupon;


//extends BaseDAO<Coupon>
public class CouponDaoImpl extends CoreDaoImpl<Coupon, Integer> implements CouponDao {

    @Override
    public int update(Coupon coupon) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Coupon SET ");

//        final String productName = product.getProductName();

//        Integer id;
//
//        Integer discount;
//
//        @Column(name = "condition_price")
//        Integer conditionPrice;
//        java.util.Date deadline;
//
//        @Column(name = "discount_code")
//        String discountCode;

        hql.append("discount = :discount,")
                .append("conditionPrice = :conditionPrice,")
                .append("deadline = :deadline,")
                .append("discountCode = :discountCode ")
                .append("WHERE id = :id");

        Query<?> query=getSession().createQuery(hql.toString());

        return query
                .setParameter("discount",coupon.getDiscount())
                .setParameter("conditionPrice",coupon.getConditionPrice())
                .setParameter("deadline",coupon.getDeadline())
                .setParameter("discountCode",coupon.getDiscountCode())
                .setParameter("id",coupon.getId())
                .executeUpdate();

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
