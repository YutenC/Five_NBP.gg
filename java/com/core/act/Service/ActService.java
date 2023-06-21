package com.core.act.Service;


import java.util.List;

import com.core.act.entity.Act;

public interface ActService {
    Act createAct(Act act); // 建立活動

    Act getActById(int actId); // 根據ID取得活動

    List<Act> getAllActs(); // 取得所有活動的列表

    Act updateAct(Act act); // 更新活動

    boolean deleteAct(int actId); // 刪除活動
}
