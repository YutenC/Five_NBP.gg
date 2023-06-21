package com.shop.shopproduct.entity;

import com.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "follow_list", schema = "five")
public class FollowList {
    @EmbeddedId
    private FollowListId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Member_id", nullable = false)
    private Member member;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;

}