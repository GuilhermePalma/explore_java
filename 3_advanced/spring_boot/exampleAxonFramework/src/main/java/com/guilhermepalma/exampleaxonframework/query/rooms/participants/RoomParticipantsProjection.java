package com.guilhermepalma.exampleaxonframework.query.rooms.participants;

import com.guilhermepalma.exampleAxonFramework.coreapi.ParticipantJoinedRoomEvent;
import com.guilhermepalma.exampleAxonFramework.coreapi.ParticipantLeftRoomEvent;
import com.guilhermepalma.exampleAxonFramework.coreapi.RoomParticipantQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomParticipantsProjection {
    private final RoomParticipantsRepository participantsRepository;

    public RoomParticipantsProjection(RoomParticipantsRepository participantsRepository) {
        this.participantsRepository = participantsRepository;
    }

    // QueryHandler: Responsavel por fazer a Manipulação das Consultas
    @QueryHandler
    public List<String> handler(RoomParticipantQuery participantQuery) {
        return participantsRepository.findRoomParticipantsByRoomId(participantQuery.getRoomId())
                .stream()
                .map(RoomParticipants::getParticipant)
                .sorted()
                .collect(Collectors.toList());
    }

    @EventHandler
    public void on(ParticipantJoinedRoomEvent joinedRoomEvent) {
        participantsRepository.save(new RoomParticipants(joinedRoomEvent.getRoomId(), joinedRoomEvent.getParticipant()));
    }

    @EventHandler
    public void on(ParticipantLeftRoomEvent leftRoomEvent) {
        participantsRepository.deleteByParticipantAndRoomId(leftRoomEvent.getParticipant(), leftRoomEvent.getRoomId());
    }


}
