package com.secondhand.sale.entity;


import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "secondhand_product", schema = "five")
public class SecondhandProduct extends Core {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer price;

    @Column
    private String content;

    @Column(name = "launch_time")
    private Date launchTime;


}
