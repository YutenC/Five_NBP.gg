package com.secondhand.sale.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "secondhand_product_image", schema = "five")
public class SecondhandProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private SecondhandProduct secondhandproduct;

    @Column
    private String image;

    @Column(name = "is_use", nullable = false)
    private Byte isUse;

}