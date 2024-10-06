package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
