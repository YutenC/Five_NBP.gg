package com.member.service;

import com.core.service.CoreService;
import com.member.entity.Black_list;

import java.util.List;

public interface BlackListService extends CoreService {

    Boolean remove(Integer id);

    List<Black_list> finAll();

    Black_list add(Black_list blackList);

    Black_list findOne(Black_list blackList);
}
