package com.shopproduct.service.impl;

import com.shopproduct.dao.ProductDao;
import com.shopproduct.dao.ProductImageDao;
import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductDetail;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.redisdao.ProductRedisDao;
import com.shopproduct.service.ProductService;
import com.shopproduct.util.ObjectInstance;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao;
    ProductImageDao productImageDao;
    ProductRedisDao productRedisDao;

    public ProductServiceImpl() {
        productDao = (ProductDao) ObjectInstance.getInstance().getObject("ProductDao");
        productImageDao = (ProductImageDao) ObjectInstance.getInstance().getObject("ProductImageDao");
        productRedisDao= (ProductRedisDao) ObjectInstance.getInstance().getObject("ProductRedisDao");
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> products = productDao.selectAll();

        for (Product p : products) {
            ProductImage productImages= getProductIndexImg(p);
            p.setProductIndexImage(productImages);
        }


        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public ProductDetail getProductDetail(Integer id) {
        Product product=productDao.selectById(id);
        List<ProductImage> productImages=productImageDao.selectByProductId(id);

        ProductDetail productDetail=  new ProductDetail(product,productImages);

        return productDetail;
    }

    @Override
    public List<Product> getProductHistory() {
        return productRedisDao.getHistoryProductBrowse();
    }

    @Override
    public void saveProductBrowseToRedis(Integer id) {
        Product product= productDao.selectById(id);
        ProductImage productImage= productImageDao.getIndexImgByProductId(id);
        product.setProductIndexImage(productImage);
        productRedisDao.saveProductBrowseToRedis(product);
    }


    public ProductImage getProductIndexImg(Product product) {
        List<ProductImage> productImages = productImageDao.selectByProductId(product.getId());
//        List<ProductImage> productImagesIndexs=new ArrayList<>();
        for (ProductImage productImage:productImages){

            if(productImage.getImage().endsWith("index.PNG")){
//                productImagesIndexs.add(productImage);
                return productImage;
//                break;
            }
        }

        return null;
    }


}
