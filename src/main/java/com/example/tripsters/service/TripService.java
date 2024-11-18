package com.example.tripsters.service;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.trip.UpdateTripRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;

import java.util.List;

public interface TripService {
    TripResponseDto createTrip(CreateTripRequestDto requestDto);

    TripResponseDto updateTrip(UpdateTripRequestDto requestDto);

    TripResponseDto addUserToTrip(Long tripId, Long userId);

    TripResponseDto leaveTrip(Long tripId);

    List<TripResponseDto> getAllTripsForCurrentUser();

    List<UserResponseDto> getAllUsersInTrip(Long tripId);

    UserResponseDto getOwnerOfTrip(Long tripId);

    void deleteTripById(Long id);
}
