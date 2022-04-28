package com.guilhermepalma.exampleAxonFramework.restapi;

import com.guilhermepalma.exampleAxonFramework.coreapi.CreateRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.JoinRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.LeaveRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.PostMessageCommand;
import com.guilhermepalma.exampleAxonFramework.entity.Message;
import com.guilhermepalma.exampleAxonFramework.entity.Participant;
import com.guilhermepalma.exampleAxonFramework.entity.Room;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("chat/")
public class CommandController {

    /**
     * Atua como Event Store. Responsavel por Redirecionar os Commands para serem executados
     */
    private final CommandGateway commandGateway;

    public CommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Operation(summary = "Create new Chat Room")
    @ApiResponse(responseCode = "200", description = "Successful Operation: Room Created")
    @PostMapping(value = "rooms", consumes = {"application/json"}, produces = {"text/plain"})
    public String createChatRoom(@RequestBody @Valid Room room) {
        String roomId = room.getRoomId() == null ? UUID.randomUUID().toString() : room.getRoomId();
        return commandGateway.sendAndWait(new CreateRoomCommand(roomId, room.getName()));
    }

    @Operation(summary = "Join in Room")
    @ApiResponse(responseCode = "200", description = "Successful Operation: Participant Joined the Room")
    @PostMapping(value = "rooms/{roomId}/participants", consumes = {"application/json"}, produces = {"text/plain"})
    public String joinChatRoom(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                               @PathVariable String roomId,
                               @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new JoinRoomCommand(roomId, participant.getName()));
    }

    @Operation(summary = "Send a Message in Chat")
    @ApiResponse(responseCode = "200", description = "Successful Operation: Posted Message")
    @PostMapping(value = "rooms/{roomId}/messages", consumes = {"application/json"}, produces = {"text/plain"})
    public String postMessage(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                              @PathVariable String roomId,
                              @RequestBody @Valid Message message) {
        return commandGateway.sendAndWait(new PostMessageCommand(roomId, message.getName(), message.getMessage()));
    }

    @Operation(summary = "Remove a User to Chat")
    @ApiResponse(responseCode = "200", description = "Successful Operation: User Removed of Room")
    @PostMapping(value = "rooms/{roomId}/leave", consumes = {"application/json"}, produces = {"text/plain"})
    public String leaveChatRoom(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                                @PathVariable String roomId,
                                @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new LeaveRoomCommand(roomId, participant.getName()));
    }
}
