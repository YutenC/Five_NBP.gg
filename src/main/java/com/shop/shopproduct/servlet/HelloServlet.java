//package com.shopproduct.servlet;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/hellohello")
//public class HelloServlet extends ThymeleafServlet {
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        String path_fileName="hello";
//        processTemplate(path_fileName,req,res);//使用thymeleaf將html做畫面渲染，使用getWriter()將渲染後的畫面輸出
//
//    }
//
//
////    @Override
////    public void doGet(HttpServletRequest req, HttpServletResponse res)
////            throws ServletException, IOException {
////
////        req.setCharacterEncoding("utf-8");
////        res.setContentType("text/html; charset=Big5");
////        PrintWriter out = res.getWriter();
////
////        String name = req.getParameter("name");
////        System.out.println(name==null);
////
////        if(name!=null) {
////            System.out.println(name.trim().length()==0);
////            System.out.println(name.trim().isEmpty());
////            System.out.println(name.trim().equals(""));
////        }
////
////        out.println("<HTML>");
////        out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
////        out.println("<BODY>");
////        out.println("Hello, 你好: " + name);
////        out.println("</BODY></HTML>");
////    }
//}
