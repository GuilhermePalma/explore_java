package com.guilhermepalma.exampleaxonframework.query.rooms.messages;

import com.guilhermepalma.exampleAxonFramework.coreapi.MessagePostedEvent;
import com.guilhermepalma.exampleAxonFramework.coreapi.RoomMessageQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;


@Component
public class ChatMessageProjection {

    private final ChatMessageRepository chatMessageRepository;

    private final QueryUpdateEmitter updateEmitter;

    public ChatMessageProjection(ChatMessageRepository chatMessageRepository, QueryUpdateEmitter updateEmitter) {
        this.chatMessageRepository = chatMessageRepository;
        this.updateEmitter = updateEmitter;
    }

    // QueryHandler: Responsavel por fazer a Manipulação das Consultas
    @QueryHandler
    public List<ChatMessage> handle(RoomMessageQuery query) {
        return chatMessageRepository.findAllByRoomIdOrderByTimestamp(query.getRoomId());
    }

    @EventHandler
    public void on(MessagePostedEvent event, @Timestamp Instant timestamp) {
        ChatMessage chatMessage = new ChatMessage(event.getRoomId(),
                event.getParticipant(), event.getMessage(), timestamp.toEpochMilli());

        chatMessageRepository.save(chatMessage);
        updateEmitter.emit(RoomMessageQuery.class, query -> query.getRoomId().equals(event.getRoomId()), chatMessage);
    }

}
