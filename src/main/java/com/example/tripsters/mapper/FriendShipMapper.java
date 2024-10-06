package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.friendship.CreateFriendShipRequestDto;
import com.example.tripsters.dto.friendship.FriendShipResponseDto;
import com.example.tripsters.model.FriendShip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface FriendShipMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    FriendShipResponseDto toDto(FriendShip friendShip);

    FriendShip toModel(CreateFriendShipRequestDto requestDto);
}
