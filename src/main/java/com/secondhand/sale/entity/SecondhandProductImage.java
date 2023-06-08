package com.secondhand.sale.entity;

import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "secondhand_product_image", schema = "five")
public class SecondhandProductImage extends Core {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "product_id")
    private Integer productId;

    @Column
    private String image;

    @Column(name = "is_use")
    private Integer isUse;


}
