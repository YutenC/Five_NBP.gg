package com.shopproduct.service;

import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductDetail;
import com.shopproduct.entity.ProductImage;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProductById(Integer id);

    ProductDetail getProductDetail(Integer id);

    List<Product> getProductHistory();

    void saveProductBrowseToRedis(Integer id);
}
