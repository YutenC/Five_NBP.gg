package com.shoporder.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SHOPPING_LIST")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingList implements java.io.Serializable{
	
	private static final long serialVersionUID = -5891042733433329198L;

	@EmbeddedId
	private PKShoppingList pkShoppingList;
	
	private Integer quantity;
}
