package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TripMapper {
    @Mapping(target = "additionalPoints", source = "additionalpoints")
    TripResponseDto toDto(Trip trip);

    @Mapping(target = "additionalpoints", source = "additionalPoints")
    Trip toModel(CreateTripRequestDto requestDto);
}
