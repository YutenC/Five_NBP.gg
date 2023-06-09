package com.shop.shopproduct.entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_image", schema = "five")
public class ProductImage {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Image_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;

    @Expose
    @Column(name = "Image", length = 2048)
    private String image;

    @Transient
    private String name;

    public ProductImage() {

    }
    public ProductImage(Product product, String image) {
        this.product = product;
        this.image = image;
    }
}