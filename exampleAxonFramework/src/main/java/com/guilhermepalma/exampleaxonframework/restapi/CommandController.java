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
import java.util.concurrent.Future;

@RestController
@RequestMapping("chat/")
public class CommandController {

    /**
     * Atua como Event Store
     */
    private final CommandGateway commandGatewayFactory;

    public CommandController(CommandGateway commandGatewayFactory) {
        this.commandGatewayFactory = commandGatewayFactory;
    }

    @PostMapping("rooms")
    public Future<String> createChatRoom(@RequestBody @Valid Room room) {
        String roomId = room.getRoomId() == null ? UUID.randomUUID().toString() : room.getRoomId();
        return commandGatewayFactory.send(new CreateRoomCommand(roomId, room.getName()));
    }

    @PostMapping("rooms/{roomId}/participants")
    public Future<String> joinChatRoom(@PathVariable String roomId, @RequestBody @Valid Participant participant) {
        return commandGatewayFactory.send(new JoinRoomCommand(roomId, participant.getName()));
    }

    @PostMapping("rooms/{roomId}/messages")
    public Future<String> postMessage(@PathVariable String roomId, @RequestBody @Valid Message message) {
        return commandGatewayFactory.send(new PostMessageCommand(roomId, message.getName(), message.getMessage()));
    }

    @PostMapping("rooms/{roomId}/leave")
    public Future<String> leaveChatRoom(@PathVariable String roomId, @RequestBody @Valid Participant participant) {
        return commandGatewayFactory.send(new LeaveRoomCommand(roomId, participant.getName()));
    }
}
