package com.example.tripsters.dto.mappoint;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMapPointRequestDto {
    @NotBlank
    private String pointName;
    private String description;
    @NotBlank
    private String pointType;
}
