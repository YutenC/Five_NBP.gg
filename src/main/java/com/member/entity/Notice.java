package com.member.entity;

import com.core.entity.Core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "notice", schema = "five")
public class Notice extends Core {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer notice_id;
    @Column
    private String notive_value;
    @Column
    private Integer member_id;
    @Column
    private Date notice_time;
    @Column
    private Boolean is_read;
}
