package com.guilhermepalma.exampleAxonFramework.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

// @TargetAggregateIdentifier: Define qual será o Campo Identificador (ID) do Agrregate

// Commands
data class CreateRoomCommand(@TargetAggregateIdentifier val roomId: String, val name: String)

data class JoinRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)

data class PostMessageCommand(@TargetAggregateIdentifier val roomId: String,
                              val participant: String, val message: String)

data class LeaveRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)

// Events
data class RoomCreatedEvent(val roomId: String, val name: String)

data class ParticipantJoinedRoomEvent(val roomId: String, val participant: String)

data class MessagePostedEvent(val roomId: String, val participant: String, val message: String)

data class ParticipantLeftRoomEvent(val roomId: String, val participant: String)

// Queries Handlers
class AllRoomsQuery

data class RoomParticipantQuery(val roomId: String)

data class RoomMessageQuery(val roomId: String)
