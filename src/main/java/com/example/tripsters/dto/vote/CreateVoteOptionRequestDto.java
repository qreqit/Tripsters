package com.example.tripsters.dto.vote;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateVoteOptionRequestDto {
    @NotBlank
    private String optionText;
}
