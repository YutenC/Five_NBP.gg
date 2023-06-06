package com.secondhand.buy.test;


import java.util.Date;

import com.secondhand.buy.dao.impl.SecondHandBuylistDaoimpl;
import com.secondhand.buy.vo.SecondHandBuylist;

public class TestApp {
	public static void main(String[] args) {
		SecondHandBuylist buylist = new SecondHandBuylist();
		buylist.setMemberId(1);
		buylist.setProductName("Switch Pro");
		buylist.setType("000");
		buylist.setContent("最新一代任天堂主機，九成新");
		buylist.setEstimate(20000);
		buylist.setPayState(0);
		buylist.setApprovalState("000");
		buylist.setApplicantBankNumber("12341234000000");
		SecondHandBuylistDaoimpl secondHandBuylistDaoimpl = new SecondHandBuylistDaoimpl();
		System.out.println
		(
				secondHandBuylistDaoimpl.selectByTime(new Date(0),new Date() )
				
				);

	}
}
