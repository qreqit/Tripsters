package com.example.tripsters.controller;

import com.example.tripsters.dto.vote.CreateVoteRequestDto;
import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.dto.vote.VoteResponseDto;
import com.example.tripsters.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public VoteResponseDto createVote(@RequestBody CreateVoteRequestDto requestDto) {
        return voteService.createVote(requestDto);
    }

    @GetMapping("/{voteId}")
    public VoteResponseDto getVote(@PathVariable Long voteId) {
        return voteService.getVote(voteId);
    }

    @GetMapping("/{voteId}/options")
    public List<VoteOptionResponseDto> getVoteOptions(@PathVariable Long voteId) {
        return voteService.getVoteOptions(voteId);
    }

    @PostMapping("/{voteId}/options/{voteOptionId}/vote")
    public VoteOptionResponseDto voteForOption(@PathVariable Long voteId, @PathVariable Long voteOptionId) {
        return voteService.voteForOption(voteId, voteOptionId);
    }
}
