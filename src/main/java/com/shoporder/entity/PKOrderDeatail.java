package com.shoporder.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Table(name = "ORDER_DETAIL")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PKOrderDeatail implements Serializable{
	
	private static final long serialVersionUID = 2L;

	@Column(name = "PRODUCT_ID", updatable = false)
	private Integer productID;
	
	@Column(name = "ORDER_ID", updatable = false)
	private Integer orderId;

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PKOrderDeatail other = (PKOrderDeatail) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(productID, other.productID);
	}
	
}
