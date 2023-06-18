package com.member.dao;

import com.core.dao.CoreDao;
import com.member.entity.Black_list;
import com.member.entity.Member;

public interface BlackListDao extends CoreDao<Black_list, Integer> {

    int insert(Black_list blackList);

    int deleteById(Integer id);

    int update(Black_list blackList);

    Black_list selectById(Integer id);
}
