package com.shopproduct.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//
@Entity
@Table(name = "product_image")
@Setter
@Getter
public class ProductImage {

    @Id
    @Column(name = "Image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "Product_id")
    Integer productId;

//    @Column(insertable=false)
    String image;

    public ProductImage(Integer productId, String image) {
        this.productId = productId;
        this.image = image;
    }

    public ProductImage() {

    }
}
