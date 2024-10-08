package com.example.tripsters.dto.chatmessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMessageDto {
    @NotNull
    private Long id;
    @NotBlank
    private String message;
}
