package com.member.service;

import com.core.service.CoreService;
import com.member.entity.Friend;

import java.util.List;

public interface FriendService extends CoreService {

    Boolean remove(Integer id);

    List<Friend> findAll();

    Friend add(Friend friend);

    Friend findOne(Friend friend);
}
