package com.example.tripsters.service;

import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.exception.RegistrationException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    List<UserResponseDto> getAllUsers();

    UserResponseDto getCurrentUser(Authentication authentication);
}
