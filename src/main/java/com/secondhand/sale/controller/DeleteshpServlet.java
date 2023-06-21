package com.secondhand.sale.controller;

import com.secondhand.sale.entity.SecondhandProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.core.util.CommonUtil.json2Pojo;
import static com.secondhand.sale.util.SecondhandProductConstants.SERVICE;

@WebServlet("/manager/shp_delete")
public class DeleteshpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        SecondhandProduct secondhandproduct = json2Pojo(req, SecondhandProduct.class);

        SERVICE.delete(secondhandproduct.getProductId());

    }
}
