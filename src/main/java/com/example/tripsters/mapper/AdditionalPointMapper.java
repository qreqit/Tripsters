package com.example.tripsters.mapper;

import com.example.tripsters.additionalpoint.AdditionalPointDto;
import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.model.AdditionalPoint;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)

public interface AdditionalPointMapper {
    AdditionalPointDto toDto(AdditionalPoint additionalPoint);

    AdditionalPoint toEntity(AdditionalPointDto additionalPointDto);
}
