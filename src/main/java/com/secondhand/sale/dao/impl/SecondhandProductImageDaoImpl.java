package com.secondhand.sale.dao.impl;

import com.secondhand.sale.dao.SecondhandProductImageDao;
import com.secondhand.sale.entity.SecondhandProduct;
import com.secondhand.sale.entity.SecondhandProductImage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class SecondhandProductImageDaoImpl implements SecondhandProductImageDao {

    private Session session = getSession();

    @Override
    public int insert(SecondhandProductImage secondhandproductimage) {
        Transaction transaction = session.beginTransaction();
        session.persist(secondhandproductimage);
        transaction.commit();
        return 1;
    }

    // 圖片全部刪除
    @Override
    public int deleteById(Integer productId) {
        Transaction transaction = session.getTransaction();
        SecondhandProductImage secondhandproductimage = session.load(SecondhandProductImage.class, productId);
        session.remove(secondhandproductimage);
        transaction.commit();
        return 1;
    }

    @Override
    public int update(SecondhandProductImage member) {
        return 0;
    }

    // 選取該筆商品單一圖片
    @Override
    public SecondhandProductImage selectById(Integer imageId) {
        final String sql = "select image_id from secondhand_product_image where product_id = ?";
//        Query<SecondhandProductImage> query = session.createQuery(hql, SecondhandProductImage.class);
//        query.setParameter("productId", productId);
////
////        List<SecondhandProductImage> result = query.list();
//
        return session.get(SecondhandProductImage.class, imageId);
    }


    // 選擇全部商品的圖片

    @Override
    public List<SecondhandProductImage> selectAll() {
        final String hql = "FROM SecondhandProductImage ORDER BY imageId";
        return session.createQuery(hql, SecondhandProductImage.class).getResultList();

//        return SecondhandProductImageDao.super.selectAll();
    }


    // 選取該筆商品全部圖片

    @Override
    public List<SecondhandProductImage> selectByProId(Integer productId) {

        final String hql = "SELECT imageId FROM SecondhandProductImage where productId = :productId";
        return session.createQuery(hql, SecondhandProductImage.class).getResultList();

    }


//    @Override
//    public List<SecondhandProductImage> selectByProId(Integer productId) {
//        final String hql = "SELECT imageId, image FROM SecondhandProductImage where productId = :productId";
//       return session.createQuery(hql, SecondhandProductImage.class).getResultList();
//    }
}
