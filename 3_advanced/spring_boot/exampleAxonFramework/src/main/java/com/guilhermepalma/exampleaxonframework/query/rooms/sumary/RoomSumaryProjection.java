package com.guilhermepalma.exampleaxonframework.query.rooms.sumary;

import com.guilhermepalma.exampleAxonFramework.coreapi.AllRoomsQuery;
import com.guilhermepalma.exampleAxonFramework.coreapi.ParticipantJoinedRoomEvent;
import com.guilhermepalma.exampleAxonFramework.coreapi.ParticipantLeftRoomEvent;
import com.guilhermepalma.exampleAxonFramework.coreapi.RoomCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomSumaryProjection {
    private final RoomSumaryRepository roomSumaryRepository;

    public RoomSumaryProjection(RoomSumaryRepository roomSumaryRepository) {
        this.roomSumaryRepository = roomSumaryRepository;
    }

    @QueryHandler // "Escuta" as Queries
    public List<RoomSumary> handler(AllRoomsQuery allRoomsQuery) {
        return roomSumaryRepository.findAll();
    }

    @EventHandler
    public void on(RoomCreatedEvent roomCreatedEvent) {
        roomSumaryRepository.save(new RoomSumary(roomCreatedEvent.getRoomId(), roomCreatedEvent.getName()));
    }

    @EventHandler // Atualiza a Informação
    public void on(ParticipantJoinedRoomEvent joinedRoomEvent) {
        roomSumaryRepository.getById(joinedRoomEvent.getRoomId()).addParticipant();
    }

    @EventHandler // Atualiza a Informação
    public void on(ParticipantLeftRoomEvent leftRoomEvent) {
        roomSumaryRepository.getById(leftRoomEvent.getRoomId()).removeParticipant();
    }


}
