package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.model.Trip;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TripMapper {
    TripResponseDto toDto(Trip trip);

    Trip toModel(CreateTripRequestDto requestDto);
}
