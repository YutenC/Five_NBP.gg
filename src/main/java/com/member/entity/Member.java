package com.member.entity;

import com.core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member", schema = "five")
public class Member extends Core {
//    public Member(){}
//    public Member (Integer member_id,String account,String password,String nick,String email,String phone,java.sql.Date birth,String id_number,String address,Double bonus,Integer member_ver_state,Date suspend_deadline,String headshot,Date ver_deadline,Integer violation){
//        this.member_id = member_id;
//        this.account = account;
//        this.password = password;
//        this.nick = nick;
//        this.email = email;
//        this.phone = phone;
//        this.birth = birth;
//        this.id_number = id_number;
//        this.address = address;
//        this.bonus = bonus;
//        this.member_ver_state = member_ver_state;
//        this.suspend_deadline = suspend_deadline;
//        this.headshot = headshot;
//        this.ver_deadline = ver_deadline;
//        this.violation = violation;
//    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer member_id;
    // 會員編號

    @Column
    private String account;
    // 會員帳號

    @Column
    private String password;
    // 會員密碼

    @Column
    private String nick;
    // 會員暱稱

    @Column
    private String email;
    // 會員Email

    @Column
    private String phone;
    // 會員手機號碼

    @Column
    private java.sql.Date birth;
    // 會員生日

    @Column
    private String id_number;
    // 會員身證證字號

    @Column
    private String address;
    // 會員地址

    @Column
    private Double bonus;
    // 累積紅利

    @Column(insertable = false)
    private Integer member_ver_state;
    // 會員驗證狀態

    @Column
    private Date suspend_deadline;
    // 會員停權期限

    @Column
    private String headshot;
    // 大頭照

    @Column(insertable = false)
    private Date ver_deadline;
    // 驗證信到期時間  可不在這做，可以存到redis就好
    // sql.Date 只存時間到日；util.Date 會存時間精準到毫秒

    @Column
    private Integer violation;
    // 被檢舉次數
}
