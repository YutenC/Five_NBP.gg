package com.power_of_manager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.power_of_manager.dao.Power_of_ManagerDAO;
import com.power_of_manager.entity.Power_of_Manager;

@Repository
public class Power_of_ManagerDAOImpl implements Power_of_ManagerDAO{
	    
    @Override
    public int insert(Power_of_Manager powerOfManager) {
        getSession().save(powerOfManager);
        return 1; // 返回插入后的主键值，如果有需要的话
    }

    @Override
    public int deleteById(Power_of_Manager.PK id) {
        Session session = getSession();
        Power_of_Manager powerOfManager = session.get(Power_of_Manager.class, id);
        if (powerOfManager != null) {
            session.remove(powerOfManager);
            return 1; // 删除成功
        }
        return 0; 
    }

    @Override
    public int update(Power_of_Manager powerOfManager) {
        Session currentSession = getSession();
        currentSession.update(powerOfManager);
        return 1; // 更新成功
    }

    @Override
    public Power_of_Manager selectById(Power_of_Manager.PK id) {
        Session currentSession = getSession();
        return currentSession.get(Power_of_Manager.class, id);
    }

    @Override
    public List<Power_of_Manager> selectAll() {
        Session currentSession = getSession();
        return currentSession.createQuery("FROM Power_of_Manager", Power_of_Manager.class).getResultList();
    }
    
    // 可以添加其他
}
