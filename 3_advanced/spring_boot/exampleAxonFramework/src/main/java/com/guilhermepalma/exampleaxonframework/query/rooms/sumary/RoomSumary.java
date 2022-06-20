package com.guilhermepalma.exampleaxonframework.query.rooms.sumary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomSumary {
    @Id
    private String roomId;

    private String name;

    private int quantityParticipants;

    public RoomSumary() {
    }

    public RoomSumary(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public int getQuantityParticipants() {
        return quantityParticipants;
    }

    public void addParticipant() {
        this.quantityParticipants++;
    }

    public void removeParticipant() {
        this.quantityParticipants--;
    }
}
