package com.core.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Core implements Serializable {
    protected static final long serialVersionUID = 1L;
    private boolean successful;
    private String message;
}
