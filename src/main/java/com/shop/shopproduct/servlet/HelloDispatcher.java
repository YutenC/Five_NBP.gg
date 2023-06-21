package com.shopproduct.servlet;

import com.shopproduct.controller.HelloJsonController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloDispatcher/*")
public class HelloDispatcher extends HttpServlet {

    HelloJsonController helloJsonCobtroller;

    public HelloDispatcher() {

        helloJsonCobtroller = new HelloJsonController();
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

        switch (path){
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
        out.println("");

    }
}
