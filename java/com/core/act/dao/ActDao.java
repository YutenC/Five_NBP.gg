package com.core.act.dao;




import com.core.act.entity.Act;

import java.util.List;

/**
 * ActDao 接口定義了對 Act 物件進行資料存取的方法。
 */
public interface ActDao {

    /**
     * 將 Act 物件插入資料庫。
     * @param act 要插入的 Act 物件。
     */
    void insert(Act act);

    /**
     * 根據 Act ID 從資料庫中刪除對應的 Act。
     *
     * @param actId 要刪除的 Act ID。
     */
    void deleteByActId(int actId);

    /**
     * 更新資料庫中對應的 Act 物件。
     *
     * @param act 要更新的 Act 物件。
     */
    void update(Act act);

    /**
     * 根據 Act ID 從資料庫中查詢對應的 Act 物件。
     *
     * @param actId 要查詢的 Act ID。
     * @return 查詢到的 Act 物件，若未找到則返回 null。
     */
    Act findByActId(int actId);

    /**
     * 查詢資料庫中的所有 Act 物件。
     *
     * @return 包含所有 Act 物件的 List。
     */
    List<Act> findAll();
}
