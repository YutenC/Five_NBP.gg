package com.member.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;

public class MemerCommonUitl {

    // 因為前端用ajax接收JSON物件，所以只要將重複的gson轉換json物件並回應的方法，拉出來寫一次就可以重複用
    public static <E> void gsonToJson(HttpServletResponse response, E entity) {
        try {
            // 創建一個Gson物件
            Gson gson = new Gson();
            // 將傳入的實體用gson的方法轉為json物件
            String gsonData = gson.toJson(entity);
            // 設定回傳資料格式為json格式
            response.setContentType("application/json");
            // 設定回傳編碼格式為UFT-8
            response.setCharacterEncoding("UTF-8");
            // 將傳送的物件寫入response後，由response送出
            response.getWriter().write(gsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
