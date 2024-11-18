package com.example.tripsters.service.impl;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.dto.trip.UpdateTripRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.exception.UnauthorizedException;
import com.example.tripsters.mapper.TripMapper;
import com.example.tripsters.mapper.UserMapper;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.TripRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.TripService;
import jakarta.transaction.Transactional;
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
    private final UserMapper userMapper;

    @Override
    @Transactional
    public TripResponseDto createTrip(CreateTripRequestDto requestDto) {
        Trip trip = tripMapper.toModel(requestDto);
        User authenticatedUser = getAuthenticatedUser();

        trip.setCreatedAt(LocalDateTime.now());
        trip.setOwnerId(authenticatedUser.getId());

        Set<User> listOfUsersInTrip = trip.getUsers();
        listOfUsersInTrip.add(authenticatedUser);

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    public TripResponseDto updateTrip(UpdateTripRequestDto requestDto) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(requestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + requestDto.getId()));

        checkOwnership(trip, authenticatedUser);
        trip.setDestination(requestDto.getDestination());
        trip.setStartDate(LocalDateTime.parse(requestDto.getStartDate()));
        trip.setEndDate(LocalDateTime.parse(requestDto.getEndDate()));
        trip.setAdditionalpoints(requestDto.getAdditionalPoints());
        trip.setStartPoint(requestDto.getStartPoint());
        trip.setEndPoint(requestDto.getEndPoint());

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    @Transactional
    public TripResponseDto addUserToTrip(Long tripId, Long userId) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not "
                        + "found with id: " + tripId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User "
                        + "not found with id: " + userId));

        checkOwnership(trip, authenticatedUser);
        if (userId.equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You are already in trip");
        }

        Set<User> listOfUserInTrip = trip.getUsers();
        listOfUserInTrip.add(user);
        tripRepository.save(trip);
        return tripMapper.toDto(trip);
    }

    @Override
    @Transactional
    public List<TripResponseDto> getAllTripsForCurrentUser() {
        User authenticatedUser = getAuthenticatedUser();
        List<Trip> trips = tripRepository
                .findByUsersId(authenticatedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can not "
                        + "found trips for this user"));

        return trips.stream()
                .map(tripMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public TripResponseDto leaveTrip(Long tripId) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + tripId));

        Set<User> listOfUsersInTrip = trip.getUsers();
        if (!trip.getOwnerId().equals(authenticatedUser.getId())) {
            if (!listOfUsersInTrip.remove(authenticatedUser)) {
                throw new EntityNotFoundException("User not "
                        + "found in the trip with id: " + tripId);
            }
            tripRepository.save(trip);
        } else {
            throw new UnauthorizedException("Owner "
                    + "cannot leave the trip");
        }

        return tripMapper.toDto(trip);
    }

    @Override
    public void deleteTripById(Long tripId) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + tripId));

        checkOwnership(trip, authenticatedUser);

        tripRepository.delete(trip);
    }

    @Override
    @Transactional
    public List<UserResponseDto> getAllUsersInTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));
        User user = getAuthenticatedUser();
        checkUserInTrip(tripId, user);

        return trip.getUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto getOwnerOfTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));
        User user = getAuthenticatedUser();
        checkUserInTrip(tripId, user);
        User owner = userRepository.findById(trip.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + trip.getOwnerId()));
        return userMapper.toDto(owner);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated "
                        + "user not found"));
    }

    private void checkOwnership(Trip trip, User user) {
        if (!trip.getOwnerId().equals(user.getId())) {
            throw new UnauthorizedException("You are not "
                    + "the owner of this trip");
        }
    }

    private void checkUserInTrip(Long tripId, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not "
                        + "found with id: " + tripId));

        if (trip.getUsers().stream().noneMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UnauthorizedException("User is "
                    + "not part of the trip");
        }
    }
}
