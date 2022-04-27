package com.guilhermepalma.exampleAxonFramework.entity;

import javax.validation.constraints.NotEmpty;

public class Room {

    private String roomId;

    @NotEmpty
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
