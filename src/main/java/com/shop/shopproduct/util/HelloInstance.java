package com.shop.shopproduct.util;

import com.shop.shopproduct.dao.impl.CouponDaoImpl;

import com.shop.shopproduct.service.impl.HelloJsonServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class HelloInstance {
    private static HelloInstance helloInstance=null;
    static Map<String,Object> map;
    static {
        helloInstance = new HelloInstance();
        map=new HashMap<>();
        init();
    }

    private HelloInstance() {
//        map=new HashMap<>();
//        init();
    }

    public static HelloInstance getInstance() {

//        if(helloInstance==null){
//            helloInstance = new HelloInstance();
//            map=new HashMap<>();
//            init();
//        }
        return helloInstance;
    }


    private static void init(){
        map.put("CouponDao",new CouponDaoImpl());
        map.put("HelloJsonService",new HelloJsonServiceImpl());

    }

    public Object getObject(String key) {
        System.out.println(map.size());
        return map.get(key);
    }
}
