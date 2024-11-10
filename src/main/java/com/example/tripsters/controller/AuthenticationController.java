package com.example.tripsters.controller;

import com.example.tripsters.dto.user.UserLoginRequestDto;
import com.example.tripsters.dto.user.UserLoginResponseDto;
import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.security.AuthenticationService;
import com.example.tripsters.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody
                                    @Valid UserRegistrationRequestDto requestBody) {
        return userService.register(requestBody);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody
                                      @Valid UserLoginRequestDto response) {
        return authenticationService.authenticate(response);
    }
}
