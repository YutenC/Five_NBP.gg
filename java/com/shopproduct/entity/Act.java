package com.shopproduct.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "act", schema = "five")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Act_id", nullable = false)
    private Integer id;

    @Column(name = "Act_name", nullable = false, length = 30)
    private String actName;

    @Column(name = "Act_time", nullable = false)
    private Instant actTime;

    @Column(name = "Act_location", length = 100)
    private String actLocation;

    @Column(name = "Act_quota", nullable = false)
    private Integer actQuota;

    @Column(name = "Act_price")
    private Integer actPrice;

    @Lob
    @Column(name = "Act_value", nullable = false)
    private String actValue;

    @Column(name = "Invite_Target", nullable = false)
    private Byte inviteTarget;

    @Column(name = "Organizer_bank_num", length = 14)
    private String organizerBankNum;

    @Column(name = "Del_from")
    private Integer delFrom;

    @Column(name = "Start_time", nullable = false)
    private Instant startTime;

    @Column(name = "End_time", nullable = false)
    private Instant endTime;

    @Column(name = "Join_number")
    private Integer joinNumber;

}