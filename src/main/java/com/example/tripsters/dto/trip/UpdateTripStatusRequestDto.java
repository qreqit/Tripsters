package com.example.tripsters.dto.trip;

import com.example.tripsters.model.Trip;
import lombok.Data;

@Data
public class UpdateTripStatusRequestDto {
    private Trip.TripStatus status;
}
