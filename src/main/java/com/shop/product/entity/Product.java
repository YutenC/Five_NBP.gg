package com.shop.product.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@Column(name = "LAUNCH_TIME")
	java.sql.Date launchTime;
	
	@OneToMany
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
	private List<ProductImage> poImages;

}
