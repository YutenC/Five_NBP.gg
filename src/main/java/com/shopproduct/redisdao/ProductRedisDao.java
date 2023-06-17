package com.shopproduct.redisdao;

import com.shopproduct.entity.Product;

import java.util.List;

public interface ProductRedisDao {
    void saveProductBrowseToRedis(Product product);
    List<Product> getHistoryProductBrowse();
}
