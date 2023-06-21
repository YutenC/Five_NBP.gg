package com.shop.shopproduct.redisdao;

import com.shop.shopproduct.entity.Product;

import java.util.List;

public interface ProductRedisDao {
    void saveProductBrowseToRedis(Product product);
    List<Product> getHistoryProductBrowse();
}
