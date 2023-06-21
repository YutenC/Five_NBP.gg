package com.secondhand.sale.entity;

import com.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "secondhand_follow_list", schema = "five")
public class SecondhandFollowList {
    @EmbeddedId
    private SecondhandFollowListId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Product_id", nullable = false)
    private SecondhandProduct product;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Member_id", nullable = false)
    private Member member;

}