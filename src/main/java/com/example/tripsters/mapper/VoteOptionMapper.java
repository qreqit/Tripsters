package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.model.VoteOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface VoteOptionMapper {
    @Mapping(source = "voteCount", target = "count")
    VoteOptionResponseDto toDto(VoteOption voteOption);
}
