package com.example.tripsters.dto.map;

import com.example.tripsters.dto.mappoint.MapPointResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MapResponseDto {
    @NotNull
    private Long id;
    private List<MapPointResponseDto> mapPoints;
}
