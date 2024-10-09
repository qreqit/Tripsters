package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
import com.example.tripsters.dto.vote.CreateVoteOptionRequestDto;
import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.model.VoteOption;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface VoteOptionMapper {
    VoteOptionResponseDto toDto(VoteOption voteOption);
}
