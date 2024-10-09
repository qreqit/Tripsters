package com.example.tripsters.dto.vote;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class CreateVoteRequestDto {
    @NotNull
    private Long tripId;
    private List<@Positive String> voteOptions;
}
