package com.shop.shoporder.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_DETAIL")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable{
	
	private static final long serialVersionUID = 1404904578291894640L;

	@EmbeddedId
	private PKOrderDeatail pkOrderDeatail;
	
	private Integer quantity;
	
	private Integer comment;
	
	@Column(name = "COMMENT_CONTENT")
	private String commentContent;
	
	@Column(name = "IS_RETURN")
	private Integer isReturn;
	
	@Column(name = "EXCHANGE_TIME")
	private Integer exchangeTime;
	
	@Column(name = "MANAGERS_ID")
	private Integer managerId;
	
	@Column(name = "TOTAL_PRICE")
	private Integer totalPrice;

}
