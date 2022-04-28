package com.guilhermepalma.exampleAxonFramework.commandModel;

import com.guilhermepalma.exampleAxonFramework.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Aggregate
public class ChatRoom {

    @AggregateIdentifier
    private String roomId;

    private Set<String> partipants;

    public ChatRoom() {
    }

    // CommandHandler: Realiza o Tratamento dos Comandos Enviados

    @CommandHandler
    public ChatRoom(CreateRoomCommand createRoomCommand) {
        AggregateLifecycle.apply(new RoomCreatedEvent(createRoomCommand.getRoomId(), createRoomCommand.getName()));
    }

    @CommandHandler
    public void handle(JoinRoomCommand joinRoomCommand) {
        if (!partipants.contains(joinRoomCommand.getParticipant())) {
            AggregateLifecycle.apply(new ParticipantJoinedRoomEvent(roomId, joinRoomCommand.getParticipant()));
        }
    }

    @CommandHandler
    public void handle(LeaveRoomCommand leaveRoomCommand) {
        if (partipants.contains(leaveRoomCommand.getParticipant())) {
            AggregateLifecycle.apply(new ParticipantLeftRoomEvent(roomId, leaveRoomCommand.getParticipant()));
        }
    }

    @CommandHandler
    public void handle(PostMessageCommand postMessageCommand) {
        Assert.state(partipants.contains(postMessageCommand.getParticipant()),
                "You cannot Post Messages without Joined in Chatroom");
        AggregateLifecycle.apply(new MessagePostedEvent(roomId,
                postMessageCommand.getParticipant(), postMessageCommand.getMessage()));
    }

    /**
     * Responsavel por "Escutar" o Evento de Criação de uma Sala.
     * Obtem o ID da Sala e Inicializa um {@link Set} vazio de Participanetes (Já que a sala ainda está vazia)
     */
    @EventSourcingHandler
    protected void on(RoomCreatedEvent roomCreatedEvent) {
        this.roomId = roomCreatedEvent.getRoomId();
        this.partipants = new HashSet<>();
    }

    @EventSourcingHandler
    protected void on(ParticipantJoinedRoomEvent participantJoinedRoomEvent) {
        this.partipants.add(participantJoinedRoomEvent.getParticipant());
    }

    @EventSourcingHandler
    protected void on(ParticipantLeftRoomEvent participantLeftRoomEvent) {
        this.partipants.remove(participantLeftRoomEvent.getParticipant());
    }


}
