package com.example.tripsters.service;

import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.exception.RegistrationException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    List<UserResponseDto> getAllUsers();

    UserResponseDto getCurrentUser(Authentication authentication);

    ResponseEntity<String> uploadImage(MultipartFile file);

    ResponseEntity<Resource> getImage(@PathVariable String filename);
}
