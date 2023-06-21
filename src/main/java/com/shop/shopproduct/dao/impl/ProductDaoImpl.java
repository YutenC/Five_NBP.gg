package com.shop.shopproduct.dao.impl;

import com.shop.shopproduct.core.CoreDaoImpl;
import com.shop.shopproduct.dao.ProductDao;
import com.shop.shopproduct.entity.Product;
import org.hibernate.query.Query;


public class ProductDaoImpl extends CoreDaoImpl<Product,Integer> implements ProductDao {

    @Override
    public int update(Product product) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Product SET ");

        final String productName = product.getProductName();

        hql.append("productName = :productName,")
                .append("type = :type,")
                .append("price = :price,")
                .append("amount = :amount,")
                .append("buyTimes = :buyTimes,")
                .append("brand = :brand,")
                .append("rate = :rate,")
                .append("revieweCount = :revieweCount,")
                .append("content = :content,")
                .append("launchTime = NOW() ")
                .append("WHERE id = :id");

        Query<?> query=getSession().createQuery(hql.toString());


        return query
                .setParameter("productName",product.getProductName())
                .setParameter("type",product.getType())
                .setParameter("price",product.getPrice())
                .setParameter("amount",product.getAmount())
                .setParameter("buyTimes",product.getBuyTimes())
                .setParameter("brand",product.getBrand())
                .setParameter("rate",product.getRate())
                .setParameter("revieweCount",product.getRevieweCount())
                .setParameter("content",product.getContent())
                .setParameter("launchTime",product.getLaunchTime())
                .setParameter("id",product.getId())
                .executeUpdate();

    }


}
