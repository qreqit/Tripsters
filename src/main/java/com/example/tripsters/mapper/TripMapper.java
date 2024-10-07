package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.user.UserDto;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class)
public interface TripMapper {
    TripResponseDto toDto(Trip trip);

    default Set<UserDto> mapUsers(Set<User> users) {
        return users.stream()
                .map(this::userToUserDto)
                .collect(Collectors.toSet());
    }

    UserDto userToUserDto(User user);
}
