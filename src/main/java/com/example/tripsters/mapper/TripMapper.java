package com.example.tripsters.mapper;

import com.example.tripsters.dto.trip.CreateTripRequestDto;
import com.example.tripsters.dto.trip.TripResponseDto;
import com.example.tripsters.model.AdditionalPoint;
import com.example.tripsters.model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = AdditionalPointMapper.class)
public interface TripMapper {
    @Mapping(target = "additionalPoints", source = "additionalPoints")
    TripResponseDto toDto(Trip trip);

    @Mapping(target = "additionalPoints", source = "additionalPoints")
    Trip toModel(CreateTripRequestDto requestDto);

    default List<AdditionalPoint> mapAdditionalPoints(List<String> additionalPoints) {
        return additionalPoints.stream()
                .map(point -> {
                    AdditionalPoint additionalPoint = new AdditionalPoint();
                    additionalPoint.setAdditionalPoint(point);
                    return additionalPoint;
                })
                .toList();
    }

    default List<String> mapAdditionalPointsToStrings(List<AdditionalPoint> additionalPoints) {
        return additionalPoints.stream()
                .map(AdditionalPoint::getAdditionalPoint)
                .toList();
    }
}
