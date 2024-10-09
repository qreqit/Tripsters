package com.example.tripsters.service.impl;

import com.example.tripsters.dto.vote.CreateVoteOptionRequestDto;
import com.example.tripsters.dto.vote.CreateVoteRequestDto;
import com.example.tripsters.dto.vote.VoteOptionResponseDto;
import com.example.tripsters.dto.vote.VoteResponseDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.exception.UnauthorizedException;
import com.example.tripsters.mapper.VoteMapper;
import com.example.tripsters.mapper.VoteOptionMapper;
import com.example.tripsters.model.Trip;
import com.example.tripsters.model.User;
import com.example.tripsters.model.Vote;
import com.example.tripsters.model.VoteOption;
import com.example.tripsters.repository.TripRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.repository.VoteOptionRepository;
import com.example.tripsters.repository.VoteRepository;
import com.example.tripsters.service.VoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;
    private final VoteMapper voteMapper;
    private final VoteOptionMapper voteOptionMapper;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    private void checkUserInTrip(Long tripId, User user) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + tripId));

        if (trip.getUsers().stream().noneMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UnauthorizedException("User is not part of the trip");
        }
    }

    @Override
    @Transactional
    public VoteResponseDto createVote(CreateVoteRequestDto requestDto) {
        User authenticatedUser = getAuthenticatedUser();
        Trip trip = tripRepository.findById(requestDto.getTripId())
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + requestDto.getTripId()));

        checkUserInTrip(requestDto.getTripId(), authenticatedUser);

        Vote vote = new Vote();
        vote.setCreatedAt(LocalDateTime.now());
        vote.setTrip(trip);
        vote.setUsers(new HashSet<>(trip.getUsers()));

        Set<VoteOption> voteOptions = requestDto.getVoteOptions().stream()
                .map(optionText -> {
                    VoteOption voteOption = new VoteOption();
                    voteOption.setOptionText(optionText);
                    voteOption.setVote(vote);
                    return voteOption;
                })
                .collect(Collectors.toSet());

        vote.setVoteOptions(voteOptions);

        voteRepository.save(vote);

        return voteMapper.toDto(vote);
    }

    @Override
    public List<VoteOptionResponseDto> getVoteOptions(Long voteId) {
        User authenticatedUser = getAuthenticatedUser();
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new EntityNotFoundException("Vote not found by id: " + voteId));

        return vote.getVoteOptions().stream()
                .map(voteOptionMapper::toDto)
                .toList();
    }

    @Override
    public VoteResponseDto getVote(Long voteId) {
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new EntityNotFoundException("Vote not found with id: " + voteId));
        return voteMapper.toDto(vote);
    }

    @Transactional
    @Override
    public VoteOptionResponseDto voteForOption(Long voteId, Long voteOptionId) {
        User authenticatedUser = getAuthenticatedUser();
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new EntityNotFoundException("Vote not found with id: " + voteId));

        checkUserInTrip(vote.getTrip().getId(), authenticatedUser);

        VoteOption voteOption = voteOptionRepository.findById(voteOptionId)
                .orElseThrow(() -> new EntityNotFoundException("Vote option not found with id: " + voteOptionId));

        voteOption.setVoteCount(voteOption.getVoteCount() + 1);
        voteOptionRepository.save(voteOption);

        return voteOptionMapper.toDto(voteOption);
    }
}
