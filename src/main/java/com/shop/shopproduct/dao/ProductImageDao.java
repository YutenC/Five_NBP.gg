package com.shop.shopproduct.dao;

import com.shop.shopproduct.core.CoreDao;
import com.shop.shopproduct.entity.ProductImage;

import java.util.List;

public interface ProductImageDao extends CoreDao<ProductImage,Integer> {
    List<ProductImage> selectByProductId(Integer id);
    ProductImage getIndexImgByProductId(Integer id);
}
