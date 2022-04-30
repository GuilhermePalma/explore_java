package com.guilhermepalma.exampleAxonFramework.restapi;

import com.google.gson.Gson;
import com.guilhermepalma.exampleAxonFramework.coreapi.CreateRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.JoinRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.LeaveRoomCommand;
import com.guilhermepalma.exampleAxonFramework.coreapi.PostMessageCommand;
import com.guilhermepalma.exampleAxonFramework.entity.Message;
import com.guilhermepalma.exampleAxonFramework.entity.Participant;
import com.guilhermepalma.exampleAxonFramework.entity.Room;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Failed Operation: An error has occurred (eg: Validation Error or Invalid Submitters)",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Exception.class)),
                            mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation: Room Created",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping(value = "rooms", consumes = {"application/json"}, produces = {"application/json"})
    public String createChatRoom(@Valid @RequestBody Room room) {
        String roomId = room.getRoomId() == null ? UUID.randomUUID().toString() : room.getRoomId();

        String roomIdCreated = commandGateway.sendAndWait(new CreateRoomCommand(roomId, room.getName()));
        Gson gson = new Gson();
        return gson.toJson(roomIdCreated);
    }

    @Operation(summary = "Join in Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Failed Operation: An error has occurred (eg: Validation Error or Invalid Submitters)",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Exception.class)),
                            mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation: Participant Joined the Room",
                    content = @Content(examples = @ExampleObject(value = "")))
    })
    @PostMapping(value = "rooms/{roomId}/participants", consumes = {"application/json"}, produces = "*/*")
    public String joinChatRoom(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                               @PathVariable String roomId,
                               @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new JoinRoomCommand(roomId, participant.getName()));
    }

    @Operation(summary = "Send a Message in Chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation: Posted Message",
                    content = @Content(examples = @ExampleObject(value = ""))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Failed Operation: An error has occurred (eg: Validation Error or Invalid Submitters)",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Exception.class)),
                            mediaType = "application/json")
            ),
    })
    @PostMapping(value = "rooms/{roomId}/messages", consumes = "application/json", produces = "*/*")
    public String postMessage(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                              @PathVariable String roomId,
                              @RequestBody @Valid Message message) {
        return commandGateway.sendAndWait(new PostMessageCommand(roomId, message.getName(), message.getMessage()));
    }

    @Operation(summary = "Remove a User to Chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation: User Removed of Room",
                    content = @Content(examples = @ExampleObject(value = ""))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Failed Operation: An error has occurred (eg: Validation Error or Invalid Submitters)",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Exception.class)),
                            mediaType = "application/json")
            ),
    })
    @PostMapping(value = "rooms/{roomId}/leave", consumes = "application/json", produces = "*/*")
    public String leaveChatRoom(@Parameter(description = "ID of Room", example = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080")
                                @PathVariable String roomId,
                                @RequestBody @Valid Participant participant) {
        return commandGateway.sendAndWait(new LeaveRoomCommand(roomId, participant.getName()));
    }
}
