//package com.shopproduct.servlet;
//
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
//import org.thymeleaf.web.servlet.JavaxServletWebApplication;
//import org.thymeleaf.web.IWebExchange;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class ThymeleafServlet extends HttpServlet {
//    private TemplateEngine templateEngine = null;
//    JavaxServletWebApplication application;
//    @Override
//    public void init() {
////        ServletContext servletContext = this.getServletContext();
//        // Templates will be resolved as application (ServletContext) resources
//
//        application = JavaxServletWebApplication.buildApplication(getServletContext());
//
//        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
//
//        // HTML is the default mode, but we will set it anyway for better understanding
//        // of code
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        // This will convert "home" to "/WEB-INF/templates/home.html"
//        templateResolver.setPrefix("/WEB-INF/pages/");
//        templateResolver.setSuffix(".html");
//        // Set template cache TTL to 1 hour. If not set, entries would live in cache
//        // until expelled by LRU
//        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
//
//        // Cache is set to true by default. Set to false if you want templates to
//        // be automatically updated when modified.
//        templateResolver.setCacheable(true);
//
//        templateResolver.setCharacterEncoding("utf-8");
//
//        final TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//
//        this.templateEngine = templateEngine;
//
//    }
//
//    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//
//
//        //設置響應內容編碼
//        resp.setContentType("text/html;charset=UTF-8");
//        req.setCharacterEncoding("UTF-8");
//
//        //建立WebContext物件
//        final IWebExchange webExchange = this.application.buildExchange(req, resp);
//        final WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
//
//        //處理模板
//        templateEngine.process(templateName, ctx, resp.getWriter());
//    }
//
//}
