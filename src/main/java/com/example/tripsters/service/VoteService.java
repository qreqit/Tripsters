package com.example.tripsters.service;

import com.example.tripsters.dto.vote.CreateVoteOptionRequestDto;
import com.example.tripsters.dto.vote.CreateVoteRequestDto;
import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.dto.vote.VoteResponseDto;

import java.util.List;

public interface VoteService {
    VoteResponseDto createVote(CreateVoteRequestDto requestDto);

    List<VoteOptionResponseDto> getVoteOptions(Long voteId);

    VoteResponseDto getVote(Long voteId);

    VoteOptionResponseDto voteForOption(Long voteId, Long voteOptionId);
}
