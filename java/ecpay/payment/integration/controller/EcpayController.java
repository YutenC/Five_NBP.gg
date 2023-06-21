package ecpay.payment.integration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shoporder.entity.OrderDetail;
import com.shoporder.entity.OrderMaster;
import com.shoporder.util.OrderDetailServiceConstant;
import com.shoporder.util.OrderMasterServiceConstant;
import com.shoporder.util.TransOrderProduct;

import ecpay.payment.integration.service.EcpayService;
import ecpay.payment.integration.service.impl.EcpayServiceImpl;

@WebServlet("/Ecpay")
public class EcpayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EcpayService ecpayService;
	private Gson gson;
	
	public EcpayController() {
        super();
        this.ecpayService = new EcpayServiceImpl();
        this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		String omIdStr = req.getParameter("orderId");
		if (omIdStr != null) {
			Integer orderId = Integer.valueOf(omIdStr);
			OrderMaster om = OrderMasterServiceConstant.OMSERVICE.getOne(orderId);
			List<TransOrderProduct> odList = OrderDetailServiceConstant.ODSERVICE.getOrderDetailByOrderId(orderId);
			
			java.sql.Date orderDate = om.getCommitDate();
			String total = om.getTotalPrice().toString();
			String tradeDescript = "MemberId:" + om.getMemberId().toString();
			String itemNames = null;
			for (TransOrderProduct trpd : odList) {
				itemNames += trpd.getProductName() + " ";
			}
//			String returnUrl = "http://211.23.128.214:5000";
			String returnUrl = "http://10.1.7.47:8081/Five_NBP.gg/EcpayResult";
			String clientReturnUrl = null;
			String extraInfoSetting = "N";
			
			String form = ecpayService.ecpayCheckOut(orderId.toString(), orderDate, total, tradeDescript, itemNames, returnUrl, clientReturnUrl, extraInfoSetting);
			pw.println(form);
			return;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		String ecpay = gson.fromJson(req.getParameter("toEcpay"), String.class);
		OrderMaster om = (OrderMaster)req.getAttribute("thisOrder");
		List<TransOrderProduct> purchaseProducts = (List<TransOrderProduct>) req.getAttribute("purchaseProducts");
		if (ecpay == "true") {
			
			String orderId = om.getOrderId().toString();
			java.sql.Date orderDate = om.getCommitDate();
			String total = om.getTotalPrice().toString();
			String tradeDescript = "MemberId:" + om.getMemberId().toString();
			String itemNames = null;
			for (TransOrderProduct trpd : purchaseProducts) {
				itemNames += trpd.getProductName() + " ";
			}
			String returnUrl = "http://211.23.128.214:5000";
			String clientReturnUrl = null;
			String extraInfoSetting = "N";
			
			pw.println(ecpayService.ecpayCheckOut(orderId, orderDate, total, tradeDescript, itemNames, returnUrl, clientReturnUrl, extraInfoSetting));
			return;
		} else {
			return;
		}
	}

}
