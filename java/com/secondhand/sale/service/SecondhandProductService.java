package com.secondhand.sale.service;

import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.dao.impl.SecondhandProductDaoImpl;
import com.secondhand.sale.entity.SecondhandOrder;
import com.secondhand.sale.entity.SecondhandProduct;

import java.util.List;

public interface SecondhandProductService {



SecondhandProduct addshp(SecondhandProduct secondhandproduct);



SecondhandProduct editshp(SecondhandProduct secondhandproduct);

boolean delete(Integer productID);

SecondhandProduct selectOne(Integer productID);

List<SecondhandProduct> searchAll();

}
