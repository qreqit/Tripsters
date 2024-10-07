package com.example.tripsters.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String createdAt;
    private boolean isDeleted;
}
