package com.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	Integer productId;
	
	@Column(name = "PRODUCT_NAME")
	String productName;
	
	String type;
	
	Integer price;
	
	Integer amount;
	
	@Column(name = "BUY_TIMES")
	Integer buyTimes;
	
	String brand;
	
	Integer rate;
	
	@Column(name = "REVIEWE_COUNT")
	Integer revieweCount;
	
	String content;
	
	@Column(name = "LAUNCH_TIMES")
	java.sql.Date launchTime;
}
