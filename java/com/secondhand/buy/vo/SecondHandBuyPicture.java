package com.secondhand.buy.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "secondhand_buy_picture" ,catalog = "five")
public class SecondHandBuyPicture implements Serializable{
	private static final long serialVersionUID = 9121233168593529556L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Integer imageId;
	
	@Column(name = "buylist_id")
	private Integer buylistId;
	
	
	private String image;
	
	
}
