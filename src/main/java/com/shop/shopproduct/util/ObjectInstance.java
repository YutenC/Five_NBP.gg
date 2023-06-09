package com.shop.shopproduct.util;

import com.shop.shopproduct.dao.impl.CouponDaoImpl;
import com.shop.shopproduct.dao.impl.ProductDaoImpl;
import com.shop.shopproduct.dao.impl.ProductImageDaoImpl;
import com.shop.shopproduct.redisdao.impl.CouponActivityRedisDaoImpl;
import com.shop.shopproduct.redisdao.impl.ProductRedisDaoImpl;
import com.shop.shopproduct.service.impl.CouponManagerServiceImpl;
import com.shop.shopproduct.service.impl.CouponServiceImpl;
import com.shop.shopproduct.service.impl.ProductManagerServiceImpl;
import com.shop.shopproduct.service.impl.ProductServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ObjectInstance {
    private static ObjectInstance objectInstance=null;
    static Map<String,Object> map;
//    static {
//        objectInstance = new ObjectInstance();
//        map=new HashMap<>();
//        init();
//    }

    private ObjectInstance() {
//        map=new HashMap<>();
//        init();
    }

    public static ObjectInstance getInstance() {

        if(objectInstance==null){
            objectInstance = new ObjectInstance();
            map=new HashMap<>();
            init();
        }
        return objectInstance;
    }


    private static void init(){

        map.put("ProductDao",new ProductDaoImpl());
        map.put("CouponDao",new CouponDaoImpl());
        map.put("CouponActivityRedisDao",new CouponActivityRedisDaoImpl());
        map.put("ProductImageDao",new ProductImageDaoImpl());
        map.put("ProductRedisDao",new ProductRedisDaoImpl());


        map.put("CouponService",new CouponServiceImpl());
        map.put("CouponManagerService",new CouponManagerServiceImpl());
        map.put("ProductService",new ProductServiceImpl());
        map.put("ProductManagerService",new ProductManagerServiceImpl());


    }

    public Object getObject(String key) {
        System.out.println(map.size());
        return map.get(key);
    }
}
