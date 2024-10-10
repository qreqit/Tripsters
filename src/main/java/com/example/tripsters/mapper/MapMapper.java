package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.map.MapResponseDto;
import com.example.tripsters.model.Map;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = MapPointMapper.class)
public interface MapMapper {
    MapResponseDto toDto(Map map);
}
