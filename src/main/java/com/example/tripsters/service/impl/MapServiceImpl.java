package com.example.tripsters.service.impl;

import com.example.tripsters.dto.map.MapResponseDto;
import com.example.tripsters.dto.mappoint.CreateMapPointRequestDto;
import com.example.tripsters.dto.mappoint.MapPointResponseDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.exception.UnauthorizedException;
import com.example.tripsters.mapper.MapMapper;
import com.example.tripsters.mapper.MapPointMapper;
import com.example.tripsters.model.Map;
import com.example.tripsters.model.MapPoint;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.MapPointRepository;
import com.example.tripsters.repository.MapRepository;
import com.example.tripsters.repository.TripRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.MapService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {
    private final MapMapper mapMapper;
    private final MapPointMapper mapPointMapper;
    private final MapRepository mapRepository;
    private final MapPointRepository mapPointRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    @Override
    @Transactional
    public MapPointResponseDto createMapPoint(CreateMapPointRequestDto requestDto, Long tripId) {
        User authenticatedUser = getAuthenticatedUser();
        checkUserInTrip(tripId, authenticatedUser);

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));

        Map map = trip.getMap();
        if (requestDto.getPointType() != null) {
            MapPoint.PointType pointType = MapPoint.PointType.valueOf(requestDto.getPointType().toUpperCase());
            if (pointType == MapPoint.PointType.START && map.getStartPoint() != null) {
                mapPointRepository.delete(map.getStartPoint());
                map.setStartPoint(null);
            }
            if (pointType == MapPoint.PointType.END && map.getEndPoint() != null) {
                mapPointRepository.delete(map.getEndPoint());
                map.setEndPoint(null);
            }
        }

    MapPoint mapPoint = mapPointMapper.toModel(requestDto);
    mapPoint.setCreatedAt(LocalDateTime.now());
    mapPoint.setMap(map);
    checkPointType(requestDto.getPointType(), tripId, mapPoint);
    mapPointRepository.save(mapPoint);

    return mapPointMapper.toDto(mapPoint);
}

    @Override
    public MapPointResponseDto getMapPointById(Long mapPointId) {
        MapPoint mapPoint = mapPointRepository.findById(mapPointId)
                .orElseThrow(() -> new EntityNotFoundException("Map "
                        + "point not foubd with id: " + mapPointId));

        return mapPointMapper.toDto(mapPoint);
    }

    @Transactional
    @Override
    public MapResponseDto getAllMapPointInTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip "
                        + "not found with id: " + tripId));
        Map map = trip.getMap();
        return mapMapper.toDto(map);
    }

    @Override
    public void deleteMapPoint(Long mapPointId, Long tripId) {
        User authenticatedUser = getAuthenticatedUser();
        checkUserInTrip(tripId, authenticatedUser);
        MapPoint mapPoint = mapPointRepository.findById(mapPointId)
                .orElseThrow(() -> new EntityNotFoundException("Map "
                        + "point not found with id: " + mapPointId));
        mapPointRepository.delete(mapPoint);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated "
                        + "user not found"));
    }

    private void checkUserInTrip(Long tripId, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not "
                        + "found with id: " + tripId));

        if (trip.getUsers().stream().noneMatch(u -> u.getEmail()
                .equals(user.getEmail()))) {
            throw new UnauthorizedException("User is "
                    + "not part of the trip");
        }
    }

    private void checkPointType(String pointType, Long tripId, MapPoint mapPoint) {
        if (pointType == null) {
            throw new EntityNotFoundException("Point type can not be null");
        }

        MapPoint.PointType actualType;
        try {
            actualType = MapPoint.PointType.valueOf(pointType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid PointType: " + pointType);
        }

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));

        mapPoint.setType(actualType);
        Map map = trip.getMap();

        if (actualType.equals(MapPoint.PointType.START)) {
            map.setStartPoint(mapPoint);
        } else if (actualType.equals(MapPoint.PointType.END)) {
            map.setEndPoint(mapPoint);
        }
    }
}
