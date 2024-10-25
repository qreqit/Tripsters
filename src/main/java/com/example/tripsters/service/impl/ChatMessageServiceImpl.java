package com.example.tripsters.service.impl;

import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.dto.chatmessage.UpdateMessageDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.exception.UnauthorizedException;
import com.example.tripsters.mapper.ChatMessageMapper;
import com.example.tripsters.model.ChatMessage;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.ChatMessageRepository;
import com.example.tripsters.repository.TripRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.ChatMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    @Transactional
    public MessageResponseDto createMessage(CreateMessageRequestDto requestDto) {
        User authenticatedUser = getAuthenticatedUser();

        Trip trip = tripRepository.findById(requestDto.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip "
                        + "not found with id: " + requestDto.getTripId()));

        checkUserInTrip(requestDto.getTripId(), authenticatedUser);

        ChatMessage chatMessage = chatMessageMapper.toModel(requestDto);
        chatMessage.setTrip(trip);
        chatMessage.setUser(authenticatedUser);
        chatMessage.setTimestamp(LocalDateTime.now());

        chatMessageRepository.save(chatMessage);
        return chatMessageMapper.toDto(chatMessage);
    }

    @Override
    @Transactional
    public MessageResponseDto updateMessage(UpdateMessageDto messageDto) {
        User authenticatedUser = getAuthenticatedUser();
        ChatMessage chatMessage = chatMessageRepository.findById(messageDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Message "
                        + "not found with id: " + messageDto.getId()));

        checkUserInTrip(chatMessage.getTrip().getId(), authenticatedUser);

        if (!chatMessage.getUser().getId()
                .equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("You are "
                    + "not the author of this message");
        }

        chatMessage.setMessage(messageDto.getMessage());
        chatMessageRepository.save(chatMessage);

        return chatMessageMapper.toDto(chatMessage);
    }

    @Override
    public MessageResponseDto getMeesageById(Long messageId) {
        ChatMessage chatMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message "
                        + "not found with id: " + messageId));
        return chatMessageMapper.toDto(chatMessage);
    }

    @Override
    public List<MessageResponseDto> getAllMessageInTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + tripId));

        List<ChatMessage> chatMessages = chatMessageRepository.findByTripId(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Chat "
                        + "messages not found by trip id: " + trip));

        return chatMessages.stream()
                .map(chatMessageMapper::toDto)
                .toList();
    }

    @Override
    public void deleteMessage(Long messageId) {
        User authenticatedUser = getAuthenticatedUser();
        ChatMessage chatMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message "
                        + "not found with id: " + messageId));

        Trip trip = chatMessage.getTrip();
        User messageOwner = chatMessage.getUser();
        User tripOwner = userRepository.findById(trip.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner of trip not found"));

        if (!authenticatedUser.getId().equals(messageOwner.getId())
                && !authenticatedUser.getId().equals(tripOwner.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this message");
        }

        chatMessageRepository.deleteById(messageId);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    private void checkUserInTrip(Long tripId, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + tripId));

        if (trip.getUsers().stream().noneMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UnauthorizedException("User is not part of the trip");
        }
    }
}
