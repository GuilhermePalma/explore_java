package com.guilhermepalma.exampleAxonFramework.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Room {

    private String roomId;

    @NotEmpty
    @Length(min = 3, max = 80)
    private String name;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
