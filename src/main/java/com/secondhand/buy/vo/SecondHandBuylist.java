package com.secondhand.buy.vo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SecondHand_Buylist" ,catalog = "five")
public class SecondHandBuylist extends com.core.entity.Core {
	private static final long serialVersionUID = -1589649673055389103L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buylist_id")
	private Integer buylistId	;
	
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "manager_id")
	private Integer managerId;
	
	@Column(name = "product_name")
	private String productName;
	
	
	private String type;
	
	private String content;
	
	private Integer estimate;
	
	private Integer price;
	
	@Column(name = "confirm_time")
	private Date confirmTime;
	
	@Column(name = "pay_state")
	private Integer payState;
	
	@Column(name = "approval_state")
	private String approvalState;
	
	@Column(name = "apply_time" , insertable = false , updatable = false)
	private Date applyTime;
	
	@Column(name = "applicant_bank_number")
	private String applicantBankNumber;

	@Transient
	@OneToMany
	@JoinColumn(name = "buylist_id",referencedColumnName = "buylist_id")
	private List<SecondHandBuyPicture> image ;
		
	
		
}
