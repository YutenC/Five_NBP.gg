package com.shop.shopproduct.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ACORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 將ServletResponse轉換為HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 設置"Access-Control-Allow-Origin"頭，允許任意來源訪問
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 設置"Access-Control-Allow-Methods"頭，允許GET，POST，PUT和DELETE方法
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 設置"Access-Control-Allow-Headers"頭，允許Content-Type頭
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // 如果請求的方法是OPTIONS，則設置狀態為204（無內容）
        String method = ((HttpServletRequest) request).getMethod();
        if ("OPTIONS".equals(method)) {
            httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        // 設定 response 的 charset 為 UTF-8
        httpResponse.setCharacterEncoding("UTF-8");
        // 設定回應的格式及字碼
        httpResponse.setContentType("application/json;charset=UTF-8");
        // 繼續過濾鏈
        chain.doFilter(request, response);
    }
}
