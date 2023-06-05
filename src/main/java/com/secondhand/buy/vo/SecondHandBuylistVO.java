package com.secondhand.buy.vo;

import java.io.Serializable;
import java.sql.Date;

public class SecondHandBuylistVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1589649673055389103L;
	private Integer buylistId	;
	private Integer memberId;
	private Integer managerId;
	private String productName;
	private String type;
	private String content;
	private Integer estimate;
	private Integer price;
	private Date confirmTime;
	private Integer payState;
	private String approvalState;
	private Date applyTime;
	private String applicantBankNumber;
	public Integer getBuylistId() {
		return buylistId;
	}
	public void setBuylistId(Integer buylistId) {
		this.buylistId = buylistId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getEstimate() {
		return estimate;
	}
	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public String getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplicantBankNumber() {
		return applicantBankNumber;
	}
	public void setApplicantBankNumber(String applicantBankNumber) {
		this.applicantBankNumber = applicantBankNumber;
	}
	
	
		
}
