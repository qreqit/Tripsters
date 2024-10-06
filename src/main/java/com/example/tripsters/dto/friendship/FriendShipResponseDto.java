package com.example.tripsters.dto.friendship;

import com.example.tripsters.model.FriendShip;
import lombok.Data;

@Data
public class FriendShipResponseDto {
    private Long id;
    private Long userId;
    private Long friendId;
    private FriendShip.Status status;
    private String createdAt;
}
