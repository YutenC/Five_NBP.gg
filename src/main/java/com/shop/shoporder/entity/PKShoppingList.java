package com.shop.shoporder.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Table(name = "SHOPPING_LIST")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PKShoppingList implements Serializable{
	
	private static final long serialVersionUID = 756936540249975661L;

	@Column(name = "MEMMBER_ID")
	private Integer memmberId;
	
	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Override
	public int hashCode() {
		return Objects.hash(memmberId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PKShoppingList other = (PKShoppingList) obj;
		return Objects.equals(memmberId, other.memmberId) && Objects.equals(productId, other.productId);
	}
	
}
