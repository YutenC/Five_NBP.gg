package com.secondhand.sale.service;

import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.dao.impl.SecondhandProductDaoImpl;
import com.secondhand.sale.entity.SecondhandOrder;
import com.secondhand.sale.entity.SecondhandProduct;

import java.util.List;

public interface SecondhandProductService {



SecondhandProduct launch(SecondhandProduct secondhandproduct);



SecondhandProduct edit(SecondhandProduct secondhandproduct);

boolean delete(Integer productID);

List<SecondhandProduct> searchAll();

}
