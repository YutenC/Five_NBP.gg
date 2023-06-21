package com.member.entity;


import com.core.entity.Core;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "login_record", schema = "five")
public class Login_record extends Core {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer login_id;
    @Id
    @Column
    private Integer member_id;
    @Column
    private String login_device;
    @Column
    private String host_name;
    @Column
    private String login_city;
    @Column
    private Date login_time;
}
