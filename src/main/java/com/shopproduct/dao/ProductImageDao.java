package com.shopproduct.dao;

import com.shopproduct.core.CoreDao;
import com.shopproduct.entity.Coupon;
import com.shopproduct.entity.ProductImage;

import java.util.List;

public interface ProductImageDao extends CoreDao<ProductImage,Integer> {
    List<ProductImage> selectByProductId(Integer id);
    ProductImage getIndexImgByProductId(Integer id);
}
