package com.example.tripsters.mapper;

import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.dto.vote.VoteResponseDto;
import com.example.tripsters.model.Vote;
import com.example.tripsters.model.VoteOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(source = "voteOptions", target = "voteOptions", qualifiedByName = "mapVoteOptions")
    VoteResponseDto toDto(Vote vote);

    @Named("mapVoteOptions")
    default List<VoteOptionResponseDto> mapVoteOptions(Set<VoteOption> voteOptions) {
        return voteOptions.stream()
                          .map(this::toDto)
                          .collect(Collectors.toList());
    }

    VoteOptionResponseDto toDto(VoteOption voteOption);
}
