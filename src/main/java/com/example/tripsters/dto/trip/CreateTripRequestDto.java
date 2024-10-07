package com.example.tripsters.dto.trip;

import lombok.Data;

@Data
public class CreateTripRequestDto {
    private String destination;
    private String startDate;
    private String endDate;
}
