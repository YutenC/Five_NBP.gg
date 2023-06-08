package com.shopproduct.servlet;

import com.shopproduct.controller.HelloJsonController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloJsonServlet_")
public class HelloJsonServlet extends HttpServlet {

    HelloJsonController helloJsonController;

    public HelloJsonServlet() {
        helloJsonController = new HelloJsonController();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        String coupon_json = helloJsonController.getAllCoupon();

        System.out.println("coupon_json: "+coupon_json);

        req.setCharacterEncoding("utf-8");

        //res.setContentType("text/html; charset=Big5");
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();

        out.println(coupon_json);

//        String name = req.getParameter("name");
//        System.out.println(name == null);
//
//        if (name != null) {
//            System.out.println(name.trim().length() == 0);
//            System.out.println(name.trim().isEmpty());
//            System.out.println(name.trim().equals(""));
//        }
//
//        out.println("<HTML>");
//        out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
//        out.println("<BODY>");
//        out.println("Hello, 你好: " + name);
//        out.println("</BODY></HTML>");
    }
}
