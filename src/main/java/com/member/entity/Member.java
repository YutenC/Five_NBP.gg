package com.member.entity;

import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member extends Core {
    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer member_id;
    @Column
    private String account;
    @Column
    private String password;
    @Column
    private String nick;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private java.sql.Date birth;
    @Column
    private String id_number;
    @Column
    private String address;
    @Column
    private Double bonus;
    @Column
    private Integer member_ver_state;
    @Column
    private Date suspend_deadline;
    @Column
    private String headshot;
    @Transient
    private Date ver_deadline;
    @Column
    private Integer violation;
}
