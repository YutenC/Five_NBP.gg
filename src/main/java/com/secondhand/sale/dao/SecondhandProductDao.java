package com.secondhand.sale.dao;

import com.core.dao.CoreDao;
import com.secondhand.sale.entity.SecondhandProduct;

import java.util.List;


public interface SecondhandProductDao extends CoreDao<SecondhandProduct, Integer> {




    @Override
    default int insert(SecondhandProduct secondhandproduct) {
        return 0;
    }

    @Override
    default int deleteById(Integer productId) {
        return 0;
    }

    @Override
    default int update(SecondhandProduct secondhandproduct) {
        return 0;
    }

    @Override
    default SecondhandProduct selectById(Integer productId) {
        return null;
    }

    @Override
    default List<SecondhandProduct> selectAll() {
        return null;
    }
}


