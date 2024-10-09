package com.example.tripsters.controller;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.trip.UpdateTripRequestDto;
import com.example.tripsters.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public TripResponseDto createTrip(@RequestBody @Valid CreateTripRequestDto requestDto) {
        return tripService.createTrip(requestDto);
    }

    @PutMapping
    public TripResponseDto updateTrip(@RequestBody UpdateTripRequestDto requestDto) {
        return tripService.updateTrip(requestDto);
    }

    @PostMapping("/{tripId}/users/{userId}")
    public TripResponseDto addUserToTrip(@PathVariable Long tripId, @PathVariable Long userId) {
        return tripService.addUserToTrip(tripId, userId);
    }

    @DeleteMapping("/leave/{tripId}")
    public void leaveTrip(@PathVariable Long tripId) {
        tripService.leaveTrip(tripId);
    }

    @DeleteMapping("/{tripId}")
    public void deleteTripById(@PathVariable Long tripId) {
        tripService.deleteTripById(tripId);
    }

    @GetMapping
    public List<TripResponseDto> getAllTripsForCurrentUser() {
        return tripService.getAllTripsForCurrentUser();
    }
}
