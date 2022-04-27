package com.guilhermepalma.exampleAxonFramework.restapi;

import com.guilhermepalma.exampleAxonFramework.coreapi.RoomMessageQuery;
import com.guilhermepalma.exampleAxonFramework.query.rooms.messages.ChatMessage;
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("rooms/")
public class QueryController {

    private final QueryGateway queryGateway;

    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("{roomId}/messages")
    public Future<List<ChatMessage>> roomMessages(@PathVariable String roomId) {
        return queryGateway.query(new RoomMessageQuery(roomId), new MultipleInstancesResponseType<>(ChatMessage.class));
    }
}
