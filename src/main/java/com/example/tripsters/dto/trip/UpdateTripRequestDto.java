package com.example.tripsters.dto.trip;

import lombok.Data;

@Data
public class UpdateTripRequestDto {
    private Long id;
    private String destination;
    private String startDate;
    private String endDate;
}