package com.guilhermepalma.exampleAxonFramework.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

// @TargetAggregateIdentifier: Define qual será o Campo Identificador (ID) do Agrregate

data class CreateRoomCommand(@TargetAggregateIdentifier val roomId: String, val name: String)

data class JoinRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)

data class PostMessageCommand(@TargetAggregateIdentifier val roomId: String,
                              val participant: String, val message: String)

data class LeaveRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)

data class RoomCreatedEvent(val roomId: String, val name: String)

data class PartipantJoinedRoomEvent(val roomId: String, val participant: String)

data class MessagePostedEvent(val roomId: String, val participant: String, val message: String)
data class PartipantLeftRoomEvent(val roomId: String, val participant: String)