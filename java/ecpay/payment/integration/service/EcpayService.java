package ecpay.payment.integration.service;

public interface EcpayService {
	
	String ecpayCheckOut(String orderId, java.sql.Date orderDate, String total,
			String tradeDescript, String itemNames, String returnUrl,
			String clientReturnUrl, String extraInfoSetting);
}
