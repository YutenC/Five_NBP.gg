package com.secondhand.sale.entity;

import com.manager.entity.Manager;
import com.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "secondhand_order", schema = "five")
public class SecondhandOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Product_id", nullable = false)
    private SecondhandProduct product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Member_id", nullable = false)
    private Member member;

    @Column(name = "Order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Manager_id")
    private Manager manager;

    @Column(name = "Deliver_state", nullable = false)
    private Byte deliverState;

    @Column(name = "Pay_state", nullable = false)
    private Byte payState;

    @Column(name = "Deliver_id", length = 30)
    private String deliverId;

    @Column(name = "Deliver_location", nullable = false, length = 50)
    private String deliverLocation;

    @Column(name = "Receive", nullable = false)
    private Byte receive;

    @Column(name = "Deliver_fee", nullable = false)
    private Integer deliverFee;

    @Column(name = "Total_price", nullable = false)
    private Integer totalPrice;

}