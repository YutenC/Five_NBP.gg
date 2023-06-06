package com.nbpggshop.model;

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
@Table(name = "FOLLOW_LIST")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PKFollowList implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "MEMBER_ID")
	private Integer memberId;
	
	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Override
	public int hashCode() {
		return Objects.hash(memberId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PKFollowList other = (PKFollowList) obj;
		return Objects.equals(memberId, other.memberId) && Objects.equals(productId, other.productId);
	}
	
	
}
