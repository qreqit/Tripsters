package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.model.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ChatMessageMapper {
    @Mapping(source = "trip.id", target = "tripId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "message", target = "message")
    MessageResponseDto toDto(ChatMessage message);

    ChatMessage toModel(CreateMessageRequestDto requestDto);
}
