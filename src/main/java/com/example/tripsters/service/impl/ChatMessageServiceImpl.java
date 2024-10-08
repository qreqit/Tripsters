package com.example.tripsters.service.impl;

import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.dto.chatmessage.UpdateMessageDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.ChatMessageRepository;
import com.example.tripsters.repository.ChatRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    @Override
    public MessageResponseDto createMessage(CreateMessageRequestDto requestDto) {

        return null;
    }

    @Override
    public MessageResponseDto updateMessage(UpdateMessageDto messageDto) {
        return null;
    }

    @Override
    public MessageResponseDto getMeesageById(Long messageId) {
        return null;
    }

    @Override
    public MessageResponseDto getAllMessageInChat(Long chatId) {
        return null;
    }

    @Override
    public void deleteMessage(Long messageId) {

    }
}
