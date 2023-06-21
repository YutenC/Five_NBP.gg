package com.shop.shopproduct.redisdao;

import java.util.List;

import com.shop.shopproduct.entity.Product;

public interface ProductRedisDao {
    void saveProductBrowseToRedis(Product product);
    List<Product> getHistoryProductBrowse();
}
