package com.shop.shopproduct.redisdao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shop.shopproduct.entity.Product;
import com.shop.shopproduct.redisdao.ProductRedisDao;
import com.shop.shopproduct.util.RedisFactory;
import com.shop.shopproduct.util.ShopProductConst;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRedisDaoImpl implements ProductRedisDao {


    @Override
    public void saveProductBrowseToRedis(Product product) {
        List<Product> products = getHistoryProductBrowse();
        Jedis jedis = RedisFactory.getRedisServiceInstance().getJedis(ShopProductConst.REDIS_SELECT_INDEX);
        for (int i=0;i<products.size();i++) {
            if (Objects.equals(products.get(i).getId(), product.getId())) {
                if(i==0){
                    return;
                }
                else if((i==(products.size()-1)) && (products.size()>=5) ){

                }
                else {
                    List<String> values= jedis.lrange("HistoryProductBrowse",i+1,-1 );
                    jedis.ltrim("HistoryProductBrowse",0,i-1);

                    String [] ss=  (String []) values.toArray(new String[0]);
                    String[] strarray = new String[values.size()];
                    values.toArray(strarray );

                    if(strarray.length!=0){
                        jedis.rpush("HistoryProductBrowse", strarray);
                    }

                }
            }
        }



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
