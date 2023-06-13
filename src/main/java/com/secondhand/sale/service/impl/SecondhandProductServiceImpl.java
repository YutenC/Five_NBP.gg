package com.secondhand.sale.service.impl;

import com.secondhand.sale.dao.SecondhandProductDao;
import com.secondhand.sale.dao.impl.SecondhandProductDaoImpl;
import com.secondhand.sale.entity.SecondhandProduct;
import com.secondhand.sale.service.SecondhandProductService;

import java.util.List;

public class SecondhandProductServiceImpl implements SecondhandProductService {

    SecondhandProductDao spdao = new SecondhandProductDaoImpl();


    @Override
    public SecondhandProduct launch(SecondhandProduct secondhandproduct) {
        if ((secondhandproduct.getName() == null)) {
            secondhandproduct.setMessage("請輸入二手商品名稱");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        }

        if ((secondhandproduct.getType().isEmpty())) {
            secondhandproduct.setMessage("請選擇二手商品欄位");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        }

        if (secondhandproduct.getPrice() == null) {
            secondhandproduct.setMessage("請輸入二手商品價格");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        } else if (secondhandproduct.getPrice() <= 0) {
            secondhandproduct.setMessage("價格輸入錯誤");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        }

        if ((secondhandproduct.getContent() == null)) {
            secondhandproduct.setMessage("請輸入二手商品內容");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        }

        final int resultCount = spdao.insert(secondhandproduct);
        if (resultCount < 1) {
            secondhandproduct.setMessage("商品上架失敗");
            secondhandproduct.setSuccessful(false);
            return secondhandproduct;
        } else {
            secondhandproduct.setMessage("商品上架成功");
            secondhandproduct.setSuccessful(true);
            return secondhandproduct;
        }
    }


    @Override
    public SecondhandProduct edit(SecondhandProduct secondhandproduct) {
        final SecondhandProduct oshproduct = spdao.selectById(secondhandproduct.getProductId());
        secondhandproduct.setName(oshproduct.getName());
        secondhandproduct.setType(oshproduct.getType());
        secondhandproduct.setPrice(oshproduct.getPrice());
        secondhandproduct.setContent(oshproduct.getContent());
        secondhandproduct.setLaunchTime(oshproduct.getLaunchTime());

        final int resultCount = spdao.update(secondhandproduct);
        secondhandproduct.setSuccessful(resultCount > 0);
        secondhandproduct.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
        return secondhandproduct;
    }

    @Override
    public boolean delete(Integer productID) {
        final int resultCount = spdao.deleteById(productID);
        return true;
    }

    @Override
    public List<SecondhandProduct> searchAll() {
        return spdao.selectAll();
    }

}// 關鍵字搜尋?
