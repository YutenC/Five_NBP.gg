package com.member.util;

import com.member.entity.Member;
import redis.clients.jedis.Jedis;

public class JedisCommonUtil {

    public static void saveCodes(Member member, String verificationCode){
        Jedis jedis = new Jedis("localhost", 6379);
        String eamil = member.getEmail();
        jedis.set(eamil, verificationCode);
        jedis.expire(eamil, 30);
        System.out.println("訊息： " + eamil + " 驗證碼： " + verificationCode);
    }

    public static Member getCodes(Member member, String verfiyCode){
        Jedis jedis = new Jedis("localhost", 6379);
        String eamil = member.getEmail();
        String checkEmail = jedis.get(eamil);

        if(checkEmail == null){
            System.out.println("訊息： 驗證碼已過期");
            member.setMessage("驗證碼已過期");
            member.setSuccessful(false);
            return member;
        }
        if(!verfiyCode.equals(checkEmail)){
            System.out.println("訊息： 驗證碼不一致");
            member.setMessage("驗證碼不一致");
            member.setSuccessful(false);
            return member;
        } else {
            System.out.println("訊息： 驗證成功");
            member.setMessage("驗證碼正確");
            member.setSuccessful(true);
            return member;
        }
    }
}
