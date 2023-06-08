package com.shopproduct.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;
    private String type;
    private Integer price;
    private Integer amount;

    @Column(name = "buy_times")
    private Integer buyTimes;

//    @Column(insertable=false)
    private String brand;
    private Integer rate;

    @Column(name = "reviewe_count")
    private Integer revieweCount;

//    @Column(insertable=false)
    private String content;

//    @Column(name = "launch_time",insertable=false)
    @Column(name = "launch_time")
    private java.util.Date launchTime;

    @Transient
    ProductImage productIndexImage;

    @Transient
    List<ProductImage> productImages;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", buyTimes=" + buyTimes +
                ", brand='" + brand + '\'' +
                ", rate=" + rate +
                ", revieweCount=" + revieweCount +
                ", content='" + content + '\'' +
                ", launchTime=" + launchTime +
                '}';
    }
}
