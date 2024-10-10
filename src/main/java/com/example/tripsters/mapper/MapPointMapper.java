package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.mappoint.CreateMapPointRequestDto;
import com.example.tripsters.dto.mappoint.MapPointResponseDto;
import com.example.tripsters.model.MapPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface MapPointMapper {
    @Mapping(source = "map.id", target = "mapId")
    MapPointResponseDto toDto(MapPoint mapPoint);

    MapPoint toModel(CreateMapPointRequestDto requestDto);
}
