package com.example.tripsters.dto.trip;

import com.example.tripsters.additionalpoint.AdditionalPointDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TripResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String destination;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    @NotBlank
    private String createdAt;
    @NotBlank
    private String startPoint;
    @NotBlank
    private String endPoint;
    private List<AdditionalPointDto> additionalPoints;
}
