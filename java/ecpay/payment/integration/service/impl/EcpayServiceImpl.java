package ecpay.payment.integration.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.service.EcpayService;

public class EcpayServiceImpl implements EcpayService{

	private AllInOne all;
	
	public EcpayServiceImpl() {
		this.all = new AllInOne("");
	}

	@Override
	public String ecpayCheckOut(String orderId, java.sql.Date orderDate, String total, String tradeDescript, String itemNames,
			String returnUrl, String clientReturnUrl, String extraInfoSetting) {
		AioCheckOutALL aio = new AioCheckOutALL();
		
		int extraId = 20 - (orderId + "NBPgg").length();
		String aioOrderId = orderId + "NBPgg" +UUID.randomUUID().toString().replaceAll("-", "").substring(0, extraId);
		aio.setMerchantTradeNo(aioOrderId);
		
		Format sfm1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		aio.setMerchantTradeDate(sfm1.format(orderDate));
		
		aio.setTotalAmount(total);		// 後端計算完前端結帳的總額後填入
		aio.setTradeDesc(tradeDescript);	// 交易描述? 輸入哪個會員購買?
		aio.setItemName(itemNames);	// 購買商品名稱字串，換行需以#分隔，最大400字
		aio.setReturnURL("http://211.23.128.214:5000");		// 接收綠界收款回覆的controller網址，controller做完資料分析及資料儲存後，回覆1|OK給綠界
//		aio.setOrderResultURL(uuId);		// 設定付款完成後消費者要看到的網頁(轉網址)
		aio.setNeedExtraPaidInfo("N");	// 是否需額外的付款資訊，如信用卡部分號碼、授權碼等
		
		String form = all.aioCheckOut(aio, null);
		return form;
	}
	
	
}
