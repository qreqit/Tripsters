package com.example.tripsters.mapper;

import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.dto.vote.VoteResponseDto;
import com.example.tripsters.model.Vote;
import com.example.tripsters.model.VoteOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class VoteMapper {

    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Mapping(source = "trip.id", target = "tripId")
    @Mapping(source = "voteOptions", target = "voteOptions", qualifiedByName = "mapVoteOptions")
    public abstract VoteResponseDto toDto(Vote vote);

    @Named("mapVoteOptions")
    protected List<VoteOptionResponseDto> mapVoteOptions(Set<VoteOption> voteOptions) {
        return voteOptions.stream()
                .map(voteOptionMapper::toDto)
                .collect(Collectors.toList());
    }
}
