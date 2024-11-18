package com.example.tripsters.dto.trip;

import com.example.tripsters.model.Trip;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTripStatusRequestDto {
    @NotBlank
    private Trip.TripStatus status;
}
