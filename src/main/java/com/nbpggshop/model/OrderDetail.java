package com.nbpggshop.model;

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
public class OrderDetail {
	
	@EmbeddedId
	private PKOrderDeatail pkOrderDeatail;
	
	private Integer qantity;
	
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
