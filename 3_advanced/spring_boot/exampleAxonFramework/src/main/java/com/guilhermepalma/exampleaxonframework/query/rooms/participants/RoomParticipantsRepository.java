package com.guilhermepalma.exampleaxonframework.query.rooms.participants;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomParticipantsRepository extends JpaRepository<RoomParticipants, Long> {
    List<RoomParticipants> findRoomParticipantsByRoomId(String roomId);

    void deleteByParticipantAndRoomId(String participant, String roomId);
}
