package com.shopproduct.servlet;

import com.google.gson.Gson;
import com.shopproduct.controller.*;
import com.shopproduct.entity.ProductImage;
import com.shopproduct.pojo.ProductPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/shopDispatcher/*")
@MultipartConfig
public class ShopDispatcherServlet extends HttpServlet {

    HelloJsonController helloJsonCobtroller;
    BackgroundMessageController backgroundMessageController;
    ProductController productController;
    CouponManagerController couponManagerController;
    ProductManagerController productManagerController;

    public ShopDispatcherServlet() {

        helloJsonCobtroller = new HelloJsonController();
        productController = new ProductController();
        couponManagerController = new CouponManagerController();
        productManagerController = new ProductManagerController();
        backgroundMessageController = new BackgroundMessageController();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        process(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.setCharacterEncoding("utf-8");

        process(req, res);

    }

    private void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        HttpSession session = req.getSession();
        String strOut = "";
        switch (path) {
            case "/addProduct":
                StringBuilder requestData = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        requestData.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String payloadData = requestData.toString();
                System.out.println(payloadData);

                Gson gson_ = new Gson();
                ProductPojo productPojo = gson_.fromJson(payloadData, ProductPojo.class);

                productManagerController.addProduct(productPojo);
//                List<ProductImage> productImages= productPojo.getNewProduct().getProductImages();
//                for (int i=0;i<productImages.size();i++) {
//                    ProductImage productImage= productImages.get(i);
//                    productImage.setImage(productImage.getImage().split(",")[1]);
//                }


                break;
            case "/uploadProduct":
                /* Receive file uploaded to the Servlet from the HTML5 form */
                Part filePart = null;
                try {
                    filePart = req.getPart("file");
                    String fileName = filePart.getSubmittedFileName();
                    for (Part part : req.getParts()) {
                        part.write("C:\\upload\\" + fileName);
                    }

                    strOut = "The file uploaded sucessfully.";

                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }


                break;
            case "/getSomeProduct":

                break;

            case "/createProductFromcsv":
                productManagerController.createProductFromcsv();
                break;
            case "/getAllProduct":
                strOut = productController.getAllProduct();
                break;
            case "/getProductDetail":
                String productId_json = req.getParameter("id");
                Gson gson = new Gson();
                Integer productId = gson.fromJson(productId_json, Integer.class);
                strOut = productController.getProductDetail(productId);
                break;

            case "/getProductHistory":
                strOut = productController.getProductHistory();
                break;

            case "/autoGenerateCouponActivity":
                couponManagerController.autoGenerateCouponActivity();
                break;

            case "/getAllCouponActivity":
                strOut = couponManagerController.getAllCouponActivity(session);
                break;
            case "/addCouponActivity":
                String newCouponActivity = req.getParameter("newCouponActivity");
                System.out.println("newCouponActivity: " + newCouponActivity);
                couponManagerController.addCouponActivity(session, newCouponActivity);
                break;

            case "/updateCouponActivity":
                String json_newCouponActivity = req.getParameter("newCouponActivity");

                strOut = couponManagerController.updateCouponActivity(json_newCouponActivity);
                break;


            case "/addCoupon":
                String json_newCoupon = req.getParameter("json_newCoupon");
                couponManagerController.addCouponActivity(session, json_newCoupon);
                break;
            case "/deleteCoupon":
                Integer couponId = Integer.parseInt(req.getParameter("couponId"));
                couponManagerController.deleteCoupon(couponId);
                break;


            case "/longTimeProcess":
                strOut = productManagerController.longTimeProcess();
                break;
            case "/getBackgroundMessage":
                String taskName = req.getParameter("taskName");
                strOut = backgroundMessageController.getBackgroundMessage(taskName);
                break;
        }

        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.println(strOut);

    }
}
