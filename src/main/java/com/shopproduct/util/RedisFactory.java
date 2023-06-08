package com.shopproduct.util;

import java.util.ArrayList;
import java.util.List;

public class RedisFactory  {

    private List<RedisContent> registerRedisService = new ArrayList<>();
    private static ThreadLocal<RedisFactory> threadLocal = new ThreadLocal<>();


    public int process() {

        if(registerRedisService!=null){
            for(RedisContent r:registerRedisService){
                r.run();
            }
        }

        return 1;
    }

    public static RedisFactory getRedisServiceInstance() {
        RedisFactory redisServiceImpl = threadLocal.get();

        if (redisServiceImpl == null) {
            redisServiceImpl = new RedisFactory();
            threadLocal.set(redisServiceImpl);
        }
        return redisServiceImpl;
    }

    public static void clear(){

        RedisFactory redisServiceImpl = threadLocal.get();

        if (redisServiceImpl != null) {


            redisServiceImpl.clearRedisService();
            threadLocal.set(null);
        }
    }

    public void registerRedisService(RedisContent redisService){
        registerRedisService.add(redisService);
    }

    public void clearRedisService(){
        if(registerRedisService!=null){
            registerRedisService.clear();
        }

    }

}
