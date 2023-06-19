package com.member.service;

import com.core.service.CoreService;
import com.member.entity.Bank;

import java.util.List;

public interface BankService extends CoreService {

    Bank edit(Bank bank);       //  編輯銀行帳戶

    Boolean remove(Integer id);     // 刪除銀行帳戶

    List<Bank> findAll();   // 查詢所有銀行帳戶

    Bank add(Bank bank);    // 新增銀行帳戶
}
