package com.example.tripsters.dto.chatmessage;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMessageRequestDto {
    @NotBlank
    private String message;
}
