package com.example.tripsters.dto.trip;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTripRequestDto {
    @NotNull
    private Long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String startPoint;
    private String endPoint;
    private List<String> additionalPoints;
}
