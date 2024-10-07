package com.example.tripsters.service;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.trip.UpdateTripRequestDto;

import java.util.List;

public interface TripService {
    TripResponseDto createTrip(CreateTripRequestDto requestDto);

    TripResponseDto updateTrip(UpdateTripRequestDto requestDto);

    TripResponseDto addUserToTrip(Long tripId, Long userId);

     TripResponseDto leaveTrip(Long tripId) ;

     List<TripResponseDto> getAllTripsForCurrentUser();

    void deleteTripById(Long id);
}
