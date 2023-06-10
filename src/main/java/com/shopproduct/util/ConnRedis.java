//package com.shopproduct.util;
//
//import redis.clients.jedis.Jedis;
//
//public class ConnRedis {
//    private static ConnRedis connRedis=null;
//    private Jedis jedis=null;
//
//
//    ConnRedis(){
//        jedis = new Jedis("localhost", 6379);
//        jedis.select(13);
//    }
//
//    public static ConnRedis getInstance() {
//        if(connRedis==null) {
//            connRedis=new ConnRedis();
//        }
//        return connRedis;
//    }
//
//
//    public Jedis getJedis() {
//        return jedis;
//    }
//}
