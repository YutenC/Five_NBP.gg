package com.secondhand.sale.controller;

import com.secondhand.sale.entity.SecondhandProduct;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.core.util.CommonUtil.json2Pojo;
import static com.core.util.CommonUtil.writePojo2Json;
import static com.secondhand.sale.util.SecondhandProductConstants.SERVICE;

@WebServlet("/html/sh_productmanageedit")
public class LaunchServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SecondhandProduct secondhandproduct = json2Pojo(req, SecondhandProduct.class);

        if (secondhandproduct == null){
            secondhandproduct.setMessage("無二手商品資訊");
            secondhandproduct.setSuccessful(false);
            writePojo2Json(resp, secondhandproduct);
            return;
        }

        secondhandproduct = SERVICE.launch(secondhandproduct);
        writePojo2Json(resp, secondhandproduct);
    }
}
