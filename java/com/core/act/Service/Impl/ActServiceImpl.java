package com.core.act.Service.Impl;


import java.util.List;

import com.core.act.Service.ActService;
import com.core.act.dao.ActDao;
import com.manager.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.act.entity.Act;

@Service
public class ActServiceImpl implements ActService {



    @Autowired

    private ActDao dao;

    @Override
    public Act createAct(Act act) {
        // 實作建立活動方法
        // ...
        return null;
    }

    @Override
    public Act getActById(int actId) {
        // 實作根據ID取得活動方法
        // ...
        return null;
    }

    @Override
    public List<Act> getAllActs() {
        // 實作取得所有活動的列表方法
        // ...
        return null;
    }

    @Override
    public Act updateAct(Act act) {
        // 實作更新活動方法
        // ...
        return null;
    }

    @Override
    public boolean deleteAct(int actId) {
        // 實作刪除活動方法
        // ...
        return false;
    }
}
