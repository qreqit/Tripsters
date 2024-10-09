package com.example.tripsters.mapper;

import com.example.tripsters.config.MapperConfig;
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

@Mapper(config = MapperConfig.class, uses = VoteOptionMapper.class)
public abstract class VoteMapper {

    @Autowired
    protected VoteOptionMapper voteOptionMapper;

    @Mapping(source = "trip.id", target = "tripId")
    @Mapping(source = "voteOptions", target = "voteOptions", qualifiedByName = "mapVoteOptions")
    public abstract VoteResponseDto toDto(Vote vote);

    @Named("mapVoteOptions")
    protected List<String> mapVoteOptions(Set<VoteOption> voteOptions) {
        return voteOptions.stream()
                .map(voteOption -> voteOptionMapper.toDto(voteOption).getOptionText())
                .collect(Collectors.toList());
    }
}