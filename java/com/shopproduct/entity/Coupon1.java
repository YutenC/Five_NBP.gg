package com.shopproduct.entity;

import java.util.Date;

public class Coupon1 {
    Integer coupon_id;
    Integer discount;
    Integer condition_price;
    java.util.Date deadline;
    String discount_code;

    public Coupon1() {
    }

    public Coupon1(Integer discount, Integer condition_price, Date deadline, String discount_code) {
        this.discount = discount;
        this.condition_price = condition_price;
        this.deadline = deadline;
        this.discount_code = discount_code;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getCondition_price() {
        return condition_price;
    }

    public void setCondition_price(Integer condition_price) {
        this.condition_price = condition_price;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }


    @Override
    public String toString() {
        return "Coupon{" +
                "coupon_id=" + coupon_id +
                ", discount=" + discount +
                ", condition_price=" + condition_price +
                ", deadline=" + deadline +
                ", discount_code='" + discount_code + '\'' +
                '}';
    }
}
