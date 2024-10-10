package com.example.tripsters.controller;

import com.example.tripsters.dto.map.MapResponseDto;
import com.example.tripsters.dto.mappoint.CreateMapPointRequestDto;
import com.example.tripsters.dto.mappoint.MapPointResponseDto;
import com.example.tripsters.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @PostMapping("/{tripId}/points")
    public ResponseEntity<MapPointResponseDto> createMapPoint(@RequestBody CreateMapPointRequestDto requestDto, @PathVariable Long tripId) {
        MapPointResponseDto responseDto = mapService.createMapPoint(requestDto, tripId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/points/{mapPointId}")
    public ResponseEntity<MapPointResponseDto> getMapPointById(@PathVariable Long mapPointId) {
        MapPointResponseDto responseDto = mapService.getMapPointById(mapPointId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{tripId}/points")
    public ResponseEntity<MapResponseDto> getAllMapPointsInTrip(@PathVariable Long tripId) {
        MapResponseDto responseDto = mapService.getAllMapPointInTrip(tripId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{tripId}/points/{mapPointId}")
    public ResponseEntity<Void> deleteMapPoint(@PathVariable Long mapPointId,@PathVariable Long tripId) {
        mapService.deleteMapPoint(mapPointId, tripId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
