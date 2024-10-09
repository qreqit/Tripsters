package com.example.tripsters.dto.vote;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateVoteRequestDto {
    @NotNull
    private Long tripId;
    private List<String> voteOptions;
}
