package com.example.tripsters.service;

import com.example.tripsters.dto.map.MapResponseDto;
import com.example.tripsters.dto.mappoint.CreateMapPointRequestDto;
import com.example.tripsters.dto.mappoint.MapPointResponseDto;

public interface MapService {
    MapPointResponseDto createMapPoint(CreateMapPointRequestDto requestDto, Long tripId);

    MapPointResponseDto getMapPointById(Long mapPointId);

    MapResponseDto getAllMapPointInTrip(Long tripId);

    void deleteMapPoint(Long mapPointId, Long tripId);
}
