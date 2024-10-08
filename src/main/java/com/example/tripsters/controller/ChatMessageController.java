package com.example.tripsters.controller;

import com.example.tripsters.dto.chatmessage.CreateMessageRequestDto;
import com.example.tripsters.dto.chatmessage.MessageResponseDto;
import com.example.tripsters.dto.chatmessage.UpdateMessageDto;
import com.example.tripsters.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody CreateMessageRequestDto requestDto) {
        MessageResponseDto responseDto = chatMessageService.createMessage(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<MessageResponseDto> updateMessage(@RequestBody UpdateMessageDto messageDto) {
        MessageResponseDto responseDto = chatMessageService.updateMessage(messageDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDto> getMessageById(@PathVariable Long id) {
        MessageResponseDto responseDto = chatMessageService.getMeesageById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<MessageResponseDto>> getAllMessagesInTrip(@PathVariable Long tripId) {
        List<MessageResponseDto> responseDtos = chatMessageService.getAllMessageInTrip(tripId);
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        chatMessageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
