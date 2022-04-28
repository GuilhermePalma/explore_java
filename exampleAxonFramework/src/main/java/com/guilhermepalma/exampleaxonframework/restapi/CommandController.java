package com.guilhermepalma.exampleAxonFramework.restapi;

import com.guilhermepalma.exampleAxonFramework.coreapi.CreateRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.JoinRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.LeaveRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.PostMessageCommand;
import com.guilhermepalma.exampleAxonFramework.entity.Message;
import com.guilhermepalma.exampleAxonFramework.entity.Participant;
import com.guilhermepalma.exampleAxonFramework.entity.Room;
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

    @PostMapping(value = "rooms", consumes = {"application/json"}, produces = {"text/plain"})
    public String createChatRoom(@RequestBody @Valid Room room) {
        String roomId = room.getRoomId() == null ? UUID.randomUUID().toString() : room.getRoomId();
        return commandGateway.sendAndWait(new CreateRoomCommand(roomId, room.getName()));
    }

    @PostMapping(value = "rooms/{roomId}/participants", consumes = {"application/json"}, produces = {"text/plain"})
    public String joinChatRoom(@PathVariable String roomId, @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new JoinRoomCommand(roomId, participant.getName()));
    }

    @PostMapping(value = "rooms/{roomId}/messages", consumes = {"application/json"}, produces = {"text/plain"})
    public String postMessage(@PathVariable String roomId, @RequestBody @Valid Message message) {
        return commandGateway.sendAndWait(new PostMessageCommand(roomId, message.getName(), message.getMessage()));
    }

    @PostMapping(value = "rooms/{roomId}/leave", consumes = {"application/json"}, produces = {"text/plain"})
    public String leaveChatRoom(@PathVariable String roomId, @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new LeaveRoomCommand(roomId, participant.getName()));
    }
}
