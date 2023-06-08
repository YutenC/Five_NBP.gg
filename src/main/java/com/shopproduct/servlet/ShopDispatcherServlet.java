package com.shopproduct.servlet;

import com.shopproduct.controller.CouponManagerController;
import com.shopproduct.controller.HelloJsonController;
import com.shopproduct.controller.ProductController;
import com.shopproduct.controller.ProductManagerController;

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
    CouponManagerController couponManagerController;
    ProductController productController;

    ProductManagerController productManagerController;

    public ShopDispatcherServlet() {

        helloJsonCobtroller = new HelloJsonController();
        couponManagerController=new CouponManagerController();
        productController=new ProductController();
        productManagerController=new ProductManagerController();
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

            case "/getAllCouponActivity":
                strOut=couponManagerController.getAllCouponActivity(session);
                break;
            case "/addCouponActivity":

                String newCouponActivity=req.getParameter("newCouponActivity");
                System.out.println("newCouponActivity: "+newCouponActivity);
                couponManagerController.addCouponActivity(session,newCouponActivity);



//                String json_newCoupon=req.getParameter("json_newCoupon");
//                helloJsonCobtroller.addCoupon(json_newCoupon);
                break;

            case "/addCoupon":
                String json_newCoupon=req.getParameter("json_newCoupon");
                helloJsonCobtroller.addCoupon(json_newCoupon);
                break;
            case "/deleteCoupon":
                Integer coupon_id=Integer.parseInt(req.getParameter("coupon_id")) ;
                helloJsonCobtroller.deleteCoupon(coupon_id);
                break;
        }

        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.println(strOut);

    }
}
