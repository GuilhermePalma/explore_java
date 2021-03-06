package com.guilhermepalma.exampleaxonframework.entity;

import javax.validation.constraints.NotEmpty;

public class Participant {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
