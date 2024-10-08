package com.example.tripsters.service;

import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.dto.chatmessage.UpdateMessageDto;

public interface ChatMessageService {
    MessageResponseDto createMessage(CreateMessageRequestDto requestDto);

    MessageResponseDto updateMessage(UpdateMessageDto messageDto);

    MessageResponseDto getMeesageById(Long messageId);

    MessageResponseDto getAllMessageInChat(Long chatId);

    void deleteMessage(Long messageId);
}
