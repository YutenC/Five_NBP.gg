package com.shopproduct.servlet;

import com.google.gson.Gson;
import com.shopproduct.controller.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/shopDispatcher/*")
public class ShopDispatcherServlet extends HttpServlet {

    HelloJsonController helloJsonCobtroller;
    BackgroundMessageController backgroundMessageController;
    ProductController productController;
    CouponManagerController couponManagerController;
    ProductManagerController productManagerController;

    public ShopDispatcherServlet() {

        helloJsonCobtroller = new HelloJsonController();
        productController=new ProductController();
        couponManagerController=new CouponManagerController();
        productManagerController=new ProductManagerController();
        backgroundMessageController=new  BackgroundMessageController();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        process(req,res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.setCharacterEncoding("utf-8");

        process(req,res);

    }

    private void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        HttpSession session =req.getSession();
        String strOut="";
        switch (path){

            case "/createProductFromcsv":
                productManagerController.createProductFromcsv();
                break;
            case "/getAllProduct":
                strOut=productController.getAllProduct();
                break;
            case "/getProductDetail":
                String productId_json=req.getParameter("id");
                Gson gson=new Gson();
                Integer productId= gson.fromJson(productId_json,Integer.class);
                strOut=productController.getProductDetail(productId);
                break;

            case "/getProductHistory":
                strOut=productController.getProductHistory();
                break;

            case "/autoGenerateCouponActivity":
                couponManagerController.autoGenerateCouponActivity();
                break;

            case "/getAllCouponActivity":
                strOut=couponManagerController.getAllCouponActivity(session);
                break;
            case "/addCouponActivity":
                String newCouponActivity=req.getParameter("newCouponActivity");
                System.out.println("newCouponActivity: "+newCouponActivity);
                couponManagerController.addCouponActivity(session,newCouponActivity);
                break;

            case "/updateCouponActivity":
                String json_newCouponActivity=req.getParameter("newCouponActivity");

                strOut=couponManagerController.updateCouponActivity(json_newCouponActivity);
                break;


            case "/addCoupon":
                String json_newCoupon=req.getParameter("json_newCoupon");
                couponManagerController.addCouponActivity(session,json_newCoupon);
                break;
            case "/deleteCoupon":
                Integer couponId=Integer.parseInt(req.getParameter("couponId")) ;
                couponManagerController.deleteCoupon(couponId);
                break;




            case "/longTimeProcess":
                strOut=productManagerController.longTimeProcess();
                break;
            case "/getBackgroundMessage":
                String taskName=req.getParameter("taskName");
                strOut= backgroundMessageController.getBackgroundMessage(taskName);
                break;
        }

        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.println(strOut);

    }
}
