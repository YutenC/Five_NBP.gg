package com.secondhand.sale.entity;


import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    @CreationTimestamp
    private Date launchTime;

    @Column(name = "Is_launch")
    private Integer isLaunch = 0;

//    @OneToMany(mappedBy = "secondhandproduct")
//    private List<SecondhandProductImage> secondhandproductimages;

}
