package com.member.util;

import com.core.service.MailService;
import com.member.service.BankService;
import com.member.service.CreditService;
import com.member.service.MemberService;
import com.member.service.impl.BankServiceImpl;
import com.member.service.impl.CreditServiceImpl;
import com.member.service.impl.MemberServiceimpl;

public class MemberConstants {
    public static final MemberService SERVICE = new MemberServiceimpl();

    public static final MailService SENDMAIL = new MailService();

    public static final BankService BANK_SERVICE = new BankServiceImpl();

    public static final CreditService CREDIT_SERVICE = new CreditServiceImpl();
}
