package com.core.act.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class ActMsg {
    @Id
    @Column(name ="Act_msg")

    private int messageId;
    @Column(name = "Message_id")
    private int memberId;
    @Column(name = "Act_id")
    private int actId;
    @Column(name = "Meg_value")
    private String msgValue;
    @Column(name = "Meg_time")
    private LocalDateTime msgTime;

    public ActMsg() {
    }

    public ActMsg(int messageId, int memberId, int actId, String msgValue, LocalDateTime msgTime) {
        this.messageId = messageId;
        this.memberId = memberId;
        this.actId = actId;
        this.msgValue = msgValue;
        this.msgTime = msgTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getMsgValue() {
        return msgValue;
    }

    public void setMsgValue(String msgValue) {
        this.msgValue = msgValue;
    }

    public LocalDateTime getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(LocalDateTime msgTime) {
        this.msgTime = msgTime;
    }
}
