package com.shop.shopproduct.controller;

import com.google.gson.Gson;
import com.shop.shopproduct.entity.CouponActivity;
import com.shop.shopproduct.entity.ErrorMsg;
import com.shop.shopproduct.entity.RequestMsg;
import com.shop.shopproduct.service.CouponManagerService;
import com.shop.shopproduct.util.ObjectInstance;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CouponManagerController {
    CouponManagerService couponManagerService;

    public  CouponManagerController(){
        couponManagerService=(CouponManagerService) ObjectInstance.getInstance().getObject("CouponManagerService");
    }

    public String getAllCouponActivity(HttpSession session) {
        System.out.println("getAllCouponActivity");
        List<CouponActivity> couponActivities=null;
        String out=null;
        Gson gson=new Gson();
        RequestMsg requestMsg=null;
        ErrorMsg errorMsg=null;

        try{
            couponActivities= couponManagerService.getAllCouponActivity();
//            out=gson.toJson(couponActivities);
//            out=couponActivities.toString();

//            List<CouponActivity> lists=new ArrayList<>();
//            for(String s: couponActivities){
//                lists.add(gson.fromJson(s,CouponActivity.class));
//            }

            requestMsg=new RequestMsg("success", couponActivities);
            out=gson.toJson(requestMsg);
        }
        catch (RuntimeException e){
            if ("Could not get a resource from the pool".equals(e.getMessage())) {
                System.out.println("Could not get a resource from the pool.........");
//                requestMsg=new RequestMsg("error", e.getMessage());
//                errorMsg=new ErrorMsg("error", e.getMessage());
            }
            requestMsg=new RequestMsg("error", e.getMessage());
            out=gson.toJson(requestMsg);
//            errorMsg=new ErrorMsg("error", e.getMessage());
//            out=gson.toJson(errorMsg);
        }



        return out;
    }

    public String addCouponActivity(HttpSession session,String newCouponActivity) {
        System.out.println("addCouponActivity");

        Gson gson=new Gson();
        CouponActivity couponActivity=gson.fromJson(newCouponActivity, CouponActivity.class);

        couponManagerService.addCouponActivity(couponActivity);
        return "";
    }

    public String updateCouponActivity(String newCouponActivity){
        Gson gson=new Gson();
        CouponActivity couponActivity=gson.fromJson(newCouponActivity, CouponActivity.class);
        couponManagerService.updateCouponActivity(couponActivity);

return "";
    }


    public void autoGenerateCouponActivity() {
        couponManagerService.generateCouponActivity();
    }


    public String deleteCoupon(Integer couponId) {
        System.out.println("deleteCoupon");
        couponManagerService.deleteCoupon(couponId);
        return "";
    }


}
