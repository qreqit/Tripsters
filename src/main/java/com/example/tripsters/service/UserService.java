package com.example.tripsters.service;

import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.exception.RegistrationException;

import java.util.List;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    List<UserResponseDto> getAllUsers();
}
