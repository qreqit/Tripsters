package com.example.tripsters.service;

import com.example.tripsters.dto.friendship.CreateFriendShipRequestDto;
import com.example.tripsters.dto.friendship.FriendShipResponseDto;
import com.example.tripsters.dto.friendship.UpdateFriendShipStatusDto;

public interface FriendShipService {
    FriendShipResponseDto addFriend(CreateFriendShipRequestDto requestDto);

    FriendShipResponseDto updateFriendShipStatus(Long id, UpdateFriendShipStatusDto statusDto);

    FriendShipResponseDto findFriendShipById(Long friendShipId);

    FriendShipResponseDto findFriendShipByFriendEmail(String friendEmail);

    void deleteFriendShip(Long friendShipId);
}
