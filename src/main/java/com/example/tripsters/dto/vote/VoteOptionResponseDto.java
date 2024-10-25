package com.example.tripsters.dto.vote;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteOptionResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String optionText;
    @Min(0)
    private Long count;
}
