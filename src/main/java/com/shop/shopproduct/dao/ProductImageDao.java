package com.shop.shopproduct.dao;

import java.util.List;

import com.shop.shopproduct.core.CoreDao;
import com.shop.shopproduct.entity.ProductImage;

public interface ProductImageDao extends CoreDao<ProductImage,Integer> {
    List<ProductImage> selectByProductId(Integer id);
    ProductImage getIndexImgByProductId(Integer id);
}
