package com.example.tripsters.dto.trip;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTripRequestDto {
    @NotBlank
    private String destination;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
}
