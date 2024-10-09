package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.vote.VoteResponseDto;
import com.example.tripsters.model.Vote;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface VoteMapper {
    VoteResponseDto toDto(Vote vote);
}
