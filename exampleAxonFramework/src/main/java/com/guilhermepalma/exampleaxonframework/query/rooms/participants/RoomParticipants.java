package com.guilhermepalma.exampleAxonFramework.query.rooms.participants;

import javax.persistence.*;

// @UniqueConstraint: Define uma Chave Unica Composta. Responsavel por Permitir que o Usuario esteja apenas 1x na Tabela

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"roomId", "participant"}))
public class RoomParticipants {

    @Id
    @GeneratedValue
    private Long id;

    private String roomId;
    private String participant;

    public RoomParticipants() {
    }

    public RoomParticipants(String roomId, String participant) {
        this.roomId = roomId;
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
