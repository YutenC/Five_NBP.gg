package com.shopproduct.pojo;

import com.google.gson.annotations.Expose;
import com.shopproduct.entity.Product;
import com.shopproduct.entity.ProductImage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Setter
@Getter
public class ProductPojo {

    Product newProduct;
}
