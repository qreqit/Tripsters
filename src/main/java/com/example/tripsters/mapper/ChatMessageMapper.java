package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.model.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ChatMessageMapper {
    MessageResponseDto toDto(ChatMessage message);

    ChatMessage toModel(CreateMessageRequestDto requestDto);
}
