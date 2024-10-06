package com.example.tripsters.dto.friendship;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateFriendShipRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long friendId;
}
