package com.shop.shopproduct.service.impl;

import java.util.List;

import com.shop.shopproduct.dao.ProductDao;
import com.shop.shopproduct.dao.ProductImageDao;
import com.shop.shopproduct.entity.Product;
import com.shop.shopproduct.entity.ProductDetail;
import com.shop.shopproduct.entity.ProductImage;
import com.shop.shopproduct.redisdao.ProductRedisDao;
import com.shop.shopproduct.service.ProductService;
import com.shop.shopproduct.util.ObjectInstance;

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
