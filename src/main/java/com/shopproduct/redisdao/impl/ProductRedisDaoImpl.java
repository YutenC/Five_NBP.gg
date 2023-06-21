package com.shopproduct.redisdao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shopproduct.entity.Product;
import com.shopproduct.redisdao.ProductRedisDao;
import com.shopproduct.util.RedisFactory;
import com.shopproduct.util.ShopProductConst;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRedisDaoImpl implements ProductRedisDao {


    @Override
    public void saveProductBrowseToRedis(Product product) {
        List<Product> products = getHistoryProductBrowse();
        for (Product p : products) {
            if (Objects.equals(p.getId(), product.getId())) {
                return;
            }
        }

        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);

        String json_str = toJson(product);
        jedis.lpush("HistoryProductBrowse", json_str);

        if (jedis.llen("HistoryProductBrowse") > 5) {
            jedis.ltrim("HistoryProductBrowse", 0, 4);
        }
    }

    @Override
    public List<Product> getHistoryProductBrowse() {
        List<Product> products = new ArrayList<Product>();

        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);
        List<String> range = jedis.lrange("HistoryProductBrowse", 0, -1);

        Gson gson = new Gson();
        for (String str : range) {
            products.add(gson.fromJson(str, Product.class));
        }

        return products;
    }

    private String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(obj);
    }
}