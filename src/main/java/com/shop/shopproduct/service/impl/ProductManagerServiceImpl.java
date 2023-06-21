package com.shopproduct.service.impl;

import com.shopproduct.common.backgroundtask.BackgroundFactory;
import com.shopproduct.common.backgroundtask.BackgroundHandler;
import com.shopproduct.core.util.HibernateUtil;
import com.shopproduct.dao.CouponDao;
import com.shopproduct.dao.ProductDao;
import com.shopproduct.dao.ProductImageDao;
import com.shopproduct.dao.impl.ProductDaoImpl;
import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.pojo.ProductPojo;
import com.shopproduct.service.ProductManagerService;
import com.shopproduct.util.ConstUtil;
import com.shopproduct.util.CreateProductDB;
import com.shopproduct.util.ObjectInstance;
import org.hibernate.Session;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Callable;

public class ProductManagerServiceImpl implements ProductManagerService {

    ProductDao productDao;
    ProductImageDao productImageDao;


    public ProductManagerServiceImpl(){
        productDao=(ProductDao) ObjectInstance.getInstance().getObject("ProductDao");
        productImageDao=(ProductImageDao) ObjectInstance.getInstance().getObject("ProductImageDao");
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

    @Override
    public void longTimeProcess(){
        BackgroundHandler backgroundHandler= BackgroundFactory.getBackgroundHandler("productBackground");

        Callable<String> task=new Callable<String>() {
            @Override
            public String call() throws Exception {

                Thread.sleep(5000);

                return "finish longTimeProcess";
            }
        };

        backgroundHandler.addTask( "readCSV",task);

    }

    @Override
    public void addProduct(ProductPojo productPojo) {
        Product product=productPojo.getNewProduct();
        Integer productId= productDao.insert(product);
        product.setId(productId);

        List<ProductImage> productImages= productPojo.getNewProduct().getProductImages();


        for (int i=0;i<productImages.size();i++) {
            ProductImage productImage= productImages.get(i);

            String fileName="";
            byte[] fromBase64str = Base64.getDecoder().decode(productImage.getImage().split(",")[1]);
            FileOutputStream fos = null;
            try {
                if(i==0){
                    fileName=productId+"_index"+".png";
                    fos = new FileOutputStream(ConstUtil.DESIMGPATH+fileName);
                }
                else {
                    fileName=productId+"_"+i+".png";
                    fos = new FileOutputStream(ConstUtil.DESIMGPATH+fileName);
                }

                fos.write(fromBase64str);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            productImage.setImage("../img/gameSoftware/test/"+fileName);
            productImage.setProduct(product);
            productImageDao.insert(productImage);
        }






    }



    String getSomeProduct(Integer pageIndex){
        List<Product>products= productDao.selectAll();

        return null;

    }

}
