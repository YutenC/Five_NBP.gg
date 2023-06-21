package com.shop.shopproduct.service;

import java.util.List;

import com.shop.shopproduct.entity.Product;
import com.shop.shopproduct.entity.ProductDetail;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProductById(Integer id);

    ProductDetail getProductDetail(Integer id);

    List<Product> getProductHistory();

    void saveProductBrowseToRedis(Integer id);
}
