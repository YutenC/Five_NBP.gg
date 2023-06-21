package com.secondhand.sale.controller;

import com.manager.entity.Manager;
import com.manager.util.ManagerConstants;
import com.secondhand.sale.entity.SecondhandProduct;
import com.secondhand.sale.util.SecondhandProductConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.core.util.CommonUtil.json2Pojo;
import static com.core.util.CommonUtil.writePojo2Json;
import static com.secondhand.sale.util.SecondhandProductConstants.SERVICE;

@WebServlet("/manager/sh_productmanage")
public class ManageshpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<SecondhandProduct> secondhandproductList = SecondhandProductConstants.SERVICE.searchAll();

        // 重新設定集合中物件的屬性(因關聯載入到sh_procudct_image，會有延遲載入的問題)
        Iterator<SecondhandProduct> iterator = secondhandproductList.iterator();
        List<SecondhandProduct> secondhandproductNewList = new ArrayList<>();
        while (iterator.hasNext()) {
            SecondhandProduct sp = iterator.next();
            SecondhandProduct newsp = new SecondhandProduct();
            newsp.setProductId(sp.getProductId());
            newsp.setIsLaunch(sp.getIsLaunch());
            newsp.setName(sp.getName());
            newsp.setType(sp.getType());
            newsp.setPrice(sp.getPrice());
//            newsp.setLaunchTime(sp.getLaunchTime());
            secondhandproductNewList.add(newsp);
            }

        writePojo2Json(resp, secondhandproductNewList);

        }
    }

