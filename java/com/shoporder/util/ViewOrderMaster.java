package com.shoporder.util;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ViewOrderMaster implements Serializable{

	private static final long serialVersionUID = 8398270951912283393L;
	
	private boolean checked;
	
	private Integer orderId;
	
	private Integer memberId;
	
	private String buyer;
	
	private String buyDate;
	
	private Integer total;
	
	private String orderStatus;
	
	private String payStatus;
	
	private String deliStatus;
	
}
