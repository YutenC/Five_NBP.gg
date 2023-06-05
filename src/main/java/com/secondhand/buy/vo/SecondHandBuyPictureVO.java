package com.secondhand.buy.vo;

import java.io.Serializable;

public class SecondHandBuyPictureVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9121233168593529556L;
	private Integer imageId;
	private Integer productId;
	private String image;
	private Integer isUse;
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	
}
