package com.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.core.entity.Core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@Data
public class Coupon extends Core{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUPON_ID", insertable = false)
	Integer couponId;
	
	Integer discount;
	
	@Column(name = "CONDITION_PRICE")
	Integer conditionPrice;
	
	java.sql.Date deadline;
	
	@Column(name = "DISCOUNT_CODE")
	String discountCode;
}
