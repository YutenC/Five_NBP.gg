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


@Entity
@Getter
@Setter
@Table(name = "friend", schema = "five")
public class Friend extends Core {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer member_id;
    @Id
    @Column
    private Integer friend_id;
    @Column
    private Boolean is_agree;

}
