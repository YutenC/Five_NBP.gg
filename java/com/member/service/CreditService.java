package com.member.service;

import com.core.service.CoreService;
import com.member.entity.Member_credit;

import java.util.List;

public interface CreditService extends CoreService {

    Member_credit edit(Member_credit member_credit);

    Boolean remove(Integer id);

    List<Member_credit> findAll();

    Member_credit add(Member_credit member_credit);
}
