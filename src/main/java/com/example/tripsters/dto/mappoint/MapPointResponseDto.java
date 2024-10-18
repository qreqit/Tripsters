package com.example.tripsters.dto.mappoint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MapPointResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String pointName;
    private String description;
    @NotBlank
    private String pointType;
    @NotNull
    private Long mapId;
}
