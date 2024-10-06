package com.example.tripsters.dto.friendship;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFriendShipRequestDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private Long friendId;
}
