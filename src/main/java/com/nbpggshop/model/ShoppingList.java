package com.nbpggshop.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SHOPPING_LIST")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingList {
	
	@EmbeddedId
	private PKShoppingList pkShoppingList;
	
	private Integer quantity;
}
