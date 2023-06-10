package com.shopproduct.service.impl;

import com.shopproduct.core.util.HibernateUtil;
import com.shopproduct.dao.CouponDao;
import com.shopproduct.dao.ProductDao;
import com.shopproduct.dao.impl.ProductDaoImpl;
import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.service.ProductManagerService;
import com.shopproduct.util.CreateProductDB;
import com.shopproduct.util.ObjectInstance;
import org.hibernate.Session;

import java.util.List;

public class ProductManagerServiceImpl implements ProductManagerService {

    ProductDao productDao;
    public ProductManagerServiceImpl(){
        productDao=(ProductDao) ObjectInstance.getInstance().getObject("ProductDao");
    }

    @Override
    public void createProductFromcsv(){

        CreateProductDB<Product, ProductImage> createProductDB=  new CreateProductDB<Product,ProductImage>(Product.class, ProductImage.class);
        try {
            List<Product> products= createProductDB.readCSV();
            for (Product value : products) {
                productDao.insert(value);
            }
            Session session= HibernateUtil.getSessionFactory().getCurrentSession();
            session.getTransaction().commit();

            session= HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            createProductDB.createImgEntity();
//            session.getTransaction().commit();


        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}