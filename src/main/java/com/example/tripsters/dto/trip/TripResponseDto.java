package com.example.tripsters.dto.trip;

import lombok.Data;

import java.util.Set;

@Data
public class TripResponseDto {
    private Long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String createdAt;
}