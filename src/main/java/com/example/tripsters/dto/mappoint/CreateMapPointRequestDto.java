package com.example.tripsters.dto.mappoint;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMapPointRequestDto {
    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;
    private String description;
}
