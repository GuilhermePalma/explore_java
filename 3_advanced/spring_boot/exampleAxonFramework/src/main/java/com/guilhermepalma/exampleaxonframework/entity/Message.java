package com.guilhermepalma.exampleaxonframework.entity;

import javax.validation.constraints.NotNull;

public class Message {
    @NotNull
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
