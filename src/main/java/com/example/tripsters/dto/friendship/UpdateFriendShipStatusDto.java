package com.example.tripsters.dto.friendship;

import com.example.tripsters.model.FriendShip;
import lombok.Data;

@Data
public class UpdateFriendShipStatusDto {
    private FriendShip.Status status;
}
