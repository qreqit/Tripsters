package com.example.tripsters.dto.chatmessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String timestamp;
    @NotNull
    private Long userId;
    @NotNull
    private Long tripId;
    @NotBlank
    private String message;
}
