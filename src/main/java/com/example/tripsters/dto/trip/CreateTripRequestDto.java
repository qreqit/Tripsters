package com.example.tripsters.dto.trip;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CreateTripRequestDto {
    @NotBlank
    private String destination;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    @NotBlank
    private String startPoint;
    @NotBlank
    private String endPoint;
    private List<String> additionalPoints;
}
