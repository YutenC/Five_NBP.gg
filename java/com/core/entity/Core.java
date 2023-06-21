package com.core.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Core implements Serializable {
    protected static final long serialVersionUID = 1L;
    private boolean successful;
    private String message;

    public Core() {
    }

    public Core(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
