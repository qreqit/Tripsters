package com.example.tripsters.dto.friendship;

import com.example.tripsters.model.FriendShip;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FriendShipResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long friendId;
    private FriendShip.Status status;
    private String createdAt;
}
