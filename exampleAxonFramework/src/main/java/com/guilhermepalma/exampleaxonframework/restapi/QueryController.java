package com.guilhermepalma.exampleAxonFramework.restapi;

import com.guilhermepalma.exampleAxonFramework.coreapi.AllRoomsQuery;
import com.guilhermepalma.exampleAxonFramework.coreapi.RoomMessageQuery;
import com.guilhermepalma.exampleAxonFramework.coreapi.RoomParticipantQuery;
import com.guilhermepalma.exampleAxonFramework.query.rooms.messages.ChatMessage;
import com.guilhermepalma.exampleAxonFramework.query.rooms.sumary.RoomSumary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

@RestController
public class QueryController {

    private final QueryGateway queryGateway;

    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Operation(summary = "Get a List of Rooms")
    @ApiResponse(responseCode = "200",
            description = "Successful Operation: Listed Rooms in Database",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoomSumary.class))))
    @GetMapping(value = "rooms", produces = {"application/json"})
    public Future<List<RoomSumary>> listRooms() {
        // Dentro do ".query()" é informado: 1° Classe Responsavel pela Query, 2° Classe de Retorno da Query
        return queryGateway.query(new AllRoomsQuery(), new MultipleInstancesResponseType<>(RoomSumary.class));
    }

    @Operation(summary = "Get a List of Participants in a Room")
    @ApiResponse(responseCode = "200",
            description = "Successful Operation: Listed Participants in Chat",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))))
    @GetMapping(value = "rooms/{roomId}", produces = {"application/json"})
    public Future<List<String>> listParticipants(@Parameter(description = "ID of Room",
            example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080") @PathVariable String roomId) {
        // Dentro do ".query()" é informado: 1° Classe Responsavel pela Query, 2° Classe de Retorno da Query
        return queryGateway.query(new RoomParticipantQuery(roomId), new MultipleInstancesResponseType<>(String.class));
    }

    @Operation(summary = "Get a List of Messages in a Room")
    @ApiResponse(responseCode = "200",
            description = "Successful Operation: Listed Messages in Room",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChatMessage.class))))
    @GetMapping(value = "rooms/{roomId}/messages", produces = {"application/json"})
    public Future<List<ChatMessage>> roomMessages(@Parameter(description = "ID of Room",
            example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080") @PathVariable String roomId) {
        return queryGateway.query(new RoomMessageQuery(roomId), new MultipleInstancesResponseType<>(ChatMessage.class));
    }
}
