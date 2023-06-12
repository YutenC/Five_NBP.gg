package com.shoporder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ORDER_MASTER")
public class OrderMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID", insertable = false, updatable = false)
	private Integer orderId;
	
	@Column(name = "MEMBER_ID", updatable = false)
	private Integer memberId;
	
	@Column(name = "COMMIT_DATE", updatable = false)
	private java.sql.Date commitDate;
	
	@Column(name = "TOTAL_PRICE", updatable = false)
	private Integer totalPrice;
	
	@Column(name = "BONUS_USE", updatable = false)
	private Integer bonusUse;
	
	@Column(name = "DELIVER_NUMBER")
	private String deliverNumber;
	
	@Column(name = "COMMIT_TYPE")
	private Integer commitType;
	
	@Column(name = "DELIVER_STATE")
	private Integer deliverState;
	
	@Column(name = "DELIVER_FEE")
	private Integer deliverFee;
	
	@Column(name = "DELIVER_LOCATION")
	private String deliverLocation;
	
	@Column(name = "PICK_TYPE")
	private Integer pickType;
	
	@Column(name = "FINISH_TIME")
	private java.sql.Date finishTime;

	@Column(name = "COUPON_ID", updatable = false)
	private Integer couponId;
	
	@Column(name = "ORDER_STATUS")
	private Integer orderStatus;
	
	@Column(name = "PAY_STATUS")
	private Integer payStatus;

}
