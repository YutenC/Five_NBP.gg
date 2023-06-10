package com.shopproduct.entity;

public class RequestMsg {

    private String state;
    private Object content;

    public RequestMsg(String state, Object content) {
        this.state = state;
        this.content = content;
    }
}
