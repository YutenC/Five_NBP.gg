package com.member.entity;

import com.core.entity.Core;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_credit", schema = "five")
public class Member_credit extends Core {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer Credit_id;
    @Column
    private Integer member_id;
    @Column
    private String cred_number;
    @Column
    private String bank_name;
}
