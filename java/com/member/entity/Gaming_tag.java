package com.member.entity;

import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gaming_tag", schema = "five")
public class Gaming_tag extends Core {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer member_id;
    @Id
    @Column
    private Integer tag_id;
}
