package com.example.tripsters.mapper;

import com.example.tripsters.additionalpoint.AdditionalPointDto;
import com.example.tripsters.model.AdditionalPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdditionalPointMapper {
    AdditionalPointDto toDto(AdditionalPoint additionalPoint);
}
