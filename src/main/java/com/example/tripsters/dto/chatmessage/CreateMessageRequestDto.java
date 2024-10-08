package com.example.tripsters.dto.chatmessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMessageRequestDto {
    @NotBlank
    private String message;
    @NotNull
    private Long tripId;
}
