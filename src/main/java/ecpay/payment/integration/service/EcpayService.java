package ecpay.payment.integration.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import com.core.service.CoreService;
import com.shop.product.dao.ProductDao;
import com.shop.product.dao.impl.ProductDaoImpl;
import com.shop.product.entity.Product;
import com.shop.shoporder.dao.OrderDetailDao;
import com.shop.shoporder.dao.OrderMasterDao;
import com.shop.shoporder.dao.impl.OrderDetailDaoImple;
import com.shop.shoporder.dao.impl.OrderMasterDaoImpl;
import com.shop.shoporder.entity.OrderDetail;
import com.shop.shoporder.entity.OrderMaster;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

public class EcpayService implements CoreService{
	
	private AioCheckOutALL aio;
	private OrderDetailDao odDao;
	private ProductDao pdDao;
	private OrderMasterDao omDao;
	
	public EcpayService() {
		this.aio = new AioCheckOutALL();
		this.odDao = new OrderDetailDaoImple();
		this.pdDao = new ProductDaoImpl();
		this.omDao = new OrderMasterDaoImpl();
	}
	
public String ecpayCheckout(Integer orderId) {
		try {
			beginTransaction();
			AllInOne all = new AllInOne("");

			OrderMaster om = omDao.selectById(orderId);
			String orderIdOpening = om.getOrderId() + "NBPgg"; 
			String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, (20 - orderIdOpening.length()));
			String fullOrderId = orderIdOpening + uuId;
			aio.setMerchantTradeNo(fullOrderId);		// 訂單編號：長度最大20，可配合隨機碼湊足
	
			Format sfm1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			aio.setMerchantTradeDate(sfm1.format(om.getCommitDate()));	// 交易時間點，後端程式碼代入
			
			aio.setTotalAmount(om.getTotalPrice().toString());		// 後端計算完前端結帳的總額後填入
			
			aio.setTradeDesc("buyer:" + om.getMemberId().toString());	// 交易描述? 輸入哪個會員購買?
			
			String shopContent = "";
			List<OrderDetail> odlist = odDao.selectByOrderId(om.getOrderId());
			for (OrderDetail od : odlist) {
				Product pd = pdDao.selectById(od.getPkOrderDeatail().getProductID());
				shopContent += pd.getProductName() + " ";
			} 
			aio.setItemName(shopContent);	// 購買商品名稱字串，需換行以#分隔，最大400字
			
			aio.setReturnURL("http://211.23.128.214:5000");		// 接收綠界收款回覆的controller網址，controller做完資料分析及資料儲存後，回覆1|OK給綠界
//			obj.setChooseSubPayment("Credit");	// 限定付款方式?
//			obj.setOrderResultURL(uuId);		// 設定付款完成後消費者要看到的網頁(轉網址)
			aio.setNeedExtraPaidInfo("N");	// 是否需額外的付款資訊，如信用卡部分號碼、授權碼等
			
			String form = all.aioCheckOut(aio, null);
			commit();
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
}
