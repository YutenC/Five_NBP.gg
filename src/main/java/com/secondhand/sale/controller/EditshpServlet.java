package com.secondhand.sale.controller;

import com.secondhand.sale.entity.SecondhandProduct;
import com.secondhand.sale.service.SecondhandProductService;

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

@WebServlet("/manager/shp_Edit")
public class EditshpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        SecondhandProduct secondhandproduct = json2Pojo(req, SecondhandProduct.class);
//        secondhandproduct = secondhandproduct.getProductId();

        SERVICE.editshp(secondhandproduct);

        if (secondhandproduct == null){
            secondhandproduct = new SecondhandProduct();
            secondhandproduct.setMessage("無二手商品資訊");
            secondhandproduct.setSuccessful(false);
            writePojo2Json(resp, secondhandproduct);
            return;
        }

        writePojo2Json(resp, secondhandproduct);

    }
}
