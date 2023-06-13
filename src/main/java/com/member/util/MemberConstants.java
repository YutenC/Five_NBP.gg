package com.member.util;

import com.core.service.MailService;
import com.member.service.MemberService;
import com.member.service.impl.MemberServiceimpl;

public class MemberConstants {
    public static final MemberService SERVICE = new MemberServiceimpl();

    public static final MailService SENDMAIL = new MailService();
}
