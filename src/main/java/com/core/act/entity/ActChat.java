package com.core.act.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;
@Entity
@Table(name = "Act_chat")
public class ActChat {
    @Id
    @Column(name = "Chat_id")
    private int chatId;

    @Column(name = "Member_id")
    private int memberId;

    @Column(name = "Act_id")
    private int actId;

    @Column(name = "Chat_content")
    private String chatContent;

    @Column(name = "Chat_time")
    private Date chatTime;

    public ActChat() {
    }

    public ActChat(int chatId, int memberId, int actId, String chatContent, Date chatTime) {
        this.chatId = chatId;
        this.memberId = memberId;
        this.actId = actId;
        this.chatContent = chatContent;
        this.chatTime = chatTime;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}
