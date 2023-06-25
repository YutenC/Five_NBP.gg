package ecpay.payment.integration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.entity.Member;
import com.member.util.MemberConstants;
import com.shop.shoporder.entity.OrderDetail;
import com.shop.shoporder.entity.OrderMaster;
import com.shop.shoporder.util.OrderDetailServiceConstant;
import com.shop.shoporder.util.ResOrderDetail;
import com.shop.shoporder.util.TransOrderProduct;

import ecpay.payment.integration.service.EcpayService;

@WebServlet("/Ecpay")
public class EcpayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EcpayController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html");
    	PrintWriter pw = res.getWriter();
    	
    	HttpSession httpSession = req.getSession();
    	Member login = new Member();
    	login.setAccount("ReimuHakurei");
    	login.setPassword("HakureiShrine");
    	Member member = MemberConstants.SERVICE.login(login);
    	httpSession.setAttribute("member", member);
    	
    	Member getMember = (Member)httpSession.getAttribute("member");
    	
    	if (getMember == null) {
    		res.sendRedirect("/Five_NBP.gg");
    		return;
    	} 
    	
    	String order = req.getParameter("orderId");
    	if (order != null) {
    		Integer orderId = Integer.valueOf(order);
    		
    		EcpayService ecpayService = new EcpayService();
    	
//    		OrderMaster om = (OrderMaster)req.getAttribute("thisOrder");
//    		System.out.println(ecpayService.ecpayCheckout(om));
//    		pw.println(ecpayService.ecpayCheckout(om));
    		
    		pw.println(ecpayService.ecpayCheckout(orderId));
    	
    		return;
    	}
	}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html");
    	
    	PrintWriter pw = res.getWriter();
    	
    	EcpayService ecpayService = new EcpayService();
    	
    	String orderInfo = req.getParameter("orderId");
    	if (orderInfo != null) {
    		System.out.println("ecpay");
    		Integer orderId = Integer.valueOf(orderInfo);
    		pw.println(ecpayService.ecpayCheckout(orderId));
    		return;
    	}
    }
}
