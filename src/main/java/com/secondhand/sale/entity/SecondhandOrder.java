package com.secondhand.sale.entity;

import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "secondhand_order", schema = "five")
public class SecondhandOrder extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "is_return")
    private Integer isReturn;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "deliver_state")
    private Integer deliverState;

    @Column(name = "deliver_id")
    private String deliverId;

    @Column(name = "deliver_location")
    private String deliverLocation;

    @Column
    private Integer receive;

    @Column(name = "deliver_fee")
    private Integer deliverFee;

    @Column(name = "total_price")
    private Integer totalPrice;


}
