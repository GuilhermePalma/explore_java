package com.guilhermepalma.exampleaxonframework.query.rooms.messages;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private long timestamp;
    private String roomId;
    private String message;
    private String participant;

    public ChatMessage() {
    }

    public ChatMessage(String roomId, String participant, String message, long timestamp) {
        this.timestamp = timestamp;
        this.roomId = roomId;
        this.participant = participant;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
