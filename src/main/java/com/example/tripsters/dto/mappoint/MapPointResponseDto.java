package com.example.tripsters.dto.mappoint;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MapPointResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private String description;
    @NotNull
    private Long mapId;
}
