package com.core.act.entity;



import com.core.entity.Core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Entity
@Table (name = "Act")
public class Act {
    /////////用小駝峰
    @Id
    @Column(name = "Act_id")
    private int actId;

    @Column(name = "Act_name")
    private String actName;

    @Column(name = "Act_time")
    private Date actTime;

    @Column(name = "Act_add")
    private String actAdd;

    @Column(name = "Act_quo")
    private int actQuo;

    @Column(name = "Act_price")
    private int actPrice;

    @Column(name = "Act_value")
    private String actValue;

    @Column(name = "Act_pass")
    private int actPass;

    @Column(name = "Org_acc")
    private String orgAcc;

    @Column(name = "Org_id")
    private int orgId;

    @Column(name = "Del_mng_id")
    private int delMngId;

    @Column(name = "Start_time")
    private Date startTime;

    @Column(name = "End_time")
    private Date endTime;

    @Column(name = "Join_number")
    private int joinNumber;



    public Act() {
    }

    public Act(int actId, String actName, Date actTime, String actAdd, int actQuo, int actPrice,
               String actValue, int actPass, String orgAcc, int orgId, int delMngId,
               Date startTime, Date endTime, int joinNumber) {
        this.actId = actId;
        this.actName = actName;
        this.actTime = actTime;
        this.actAdd = actAdd;
        this.actQuo = actQuo;
        this.actPrice = actPrice;
        this.actValue = actValue;
        this.actPass = actPass;
        this.orgAcc = orgAcc;
        this.orgId = orgId;
        this.delMngId = delMngId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.joinNumber = joinNumber;
    }


    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Date getActTime() {
        return actTime;
    }

    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }

    public String getActAdd() {
        return actAdd;
    }

    public void setActAdd(String actAdd) {
        this.actAdd = actAdd;
    }

    public int getActQuo() {
        return actQuo;
    }

    public void setActQuo(int actQuo) {
        this.actQuo = actQuo;
    }

    public int getActPrice() {
        return actPrice;
    }

    public void setActPrice(int actPrice) {
        this.actPrice = actPrice;
    }

    public String getActValue() {
        return actValue;
    }

    public void setActValue(String actValue) {
        this.actValue = actValue;
    }

    public int getActPass() {
        return actPass;
    }

    public void setActPass(int actPass) {
        this.actPass = actPass;
    }

    public String getOrgAcc() {
        return orgAcc;
    }

    public void setOrgAcc(String orgAcc) {
        this.orgAcc = orgAcc;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getDelMngId() {
        return delMngId;
    }

    public void setDelMngId(int delMngId) {
        this.delMngId = delMngId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(int joinNumber) {
        this.joinNumber = joinNumber;
    }
}