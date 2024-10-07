package com.example.tripsters.service.impl;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.trip.UpdateTripRequestDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.mapper.TripMapper;
import com.example.tripsters.model.Chat;
import com.example.tripsters.model.Map;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import com.example.tripsters.model.Vote;
import com.example.tripsters.repository.TripRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    @Override
    public TripResponseDto createTrip(CreateTripRequestDto requestDto) {
        Trip trip = new Trip();
        User authenticatedUser = getAuthenticatedUser();
        Chat tripChat = new Chat();
        Map tripMap = new Map();

        trip.setDestination(requestDto.getDestination());
        trip.setStartDate(LocalDateTime.parse(requestDto.getStartDate()));
        trip.setEndDate(LocalDateTime.parse(requestDto.getEndDate()));
        trip.setCreatedAt(LocalDateTime.now());
        trip.setChat(tripChat);
        trip.setMap(tripMap);

        Set<User> listOfUsersInTrip = trip.getUsers();
        listOfUsersInTrip.add(authenticatedUser);

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    public TripResponseDto updateTrip(UpdateTripRequestDto requestDto) {
        Trip trip = tripRepository.findById(requestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + requestDto.getId()));

        trip.setDestination(requestDto.getDestination());
        trip.setStartDate(LocalDateTime.parse(requestDto.getStartDate()));
        trip.setEndDate(LocalDateTime.parse(requestDto.getEndDate()));

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    public TripResponseDto addUserToTrip(Long tripId, Long userId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Set<User> listOfUserInTrip = trip.getUsers();
        listOfUserInTrip.add(user);
        tripRepository.save(trip);
        return tripMapper.toDto(trip);
    }

    @Override
    public List<TripResponseDto> getAllTripsForCurrentUser() {
        User authenticatedUser = getAuthenticatedUser();
        List<Trip> trips = tripRepository.findByUsersId(authenticatedUser.getId())
                .orElseThrow(() -> new  EntityNotFoundException("Can not found trips for this user"));

        return trips.stream()
                .map(tripMapper::toDto)
                .toList();
    }

    @Override
    public TripResponseDto leaveTrip(Long tripId) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));

        Set<User> listOfUsersInTrip = trip.getUsers();
        if (!listOfUsersInTrip.remove(authenticatedUser)) {
            throw new EntityNotFoundException("User not found in the trip with id: " + tripId);
        }

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    public void deleteTripById(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));

        tripRepository.delete(trip);
    }
}
