package com.example.tripsters.service;

import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.dto.chatmessage.UpdateMessageDto;

import java.util.List;

public interface ChatMessageService {
    MessageResponseDto createMessage(CreateMessageRequestDto requestDto);

    MessageResponseDto updateMessage(UpdateMessageDto messageDto);

    MessageResponseDto getMeesageById(Long messageId);

    List<MessageResponseDto> getAllMessageInTrip(Long tripId);

    void deleteMessage(Long messageId);
}
