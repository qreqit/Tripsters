package com.example.tripsters.service.impl;

import com.example.tripsters.dto.friendship.CreateFriendShipRequestDto;
import com.example.tripsters.dto.friendship.FriendShipResponseDto;
import com.example.tripsters.dto.friendship.UpdateFriendShipStatusDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.mapper.FriendShipMapper;
import com.example.tripsters.model.FriendShip;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.FriendShipRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.FriendShipService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendShipServiceImpl implements FriendShipService {
    private final UserRepository userRepository;
    private final FriendShipRepository friendShipRepository;
    private final FriendShipMapper friendShipMapper;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    @Override
    public FriendShipResponseDto addFriend(CreateFriendShipRequestDto requestDto) {
        User authenticatedUser = getAuthenticatedUser();

        if (!authenticatedUser.getId().equals(requestDto.getUserId())
                && !authenticatedUser.getId().equals(requestDto.getFriendId())) {
            throw new IllegalArgumentException("You can only add friends to your own account.");
        }

        FriendShip friendShip = new FriendShip();

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Can not "
                        + "find user with id" + requestDto.getUserId()));

        User friend = userRepository.findById(requestDto.getFriendId())
                .orElseThrow(() -> new EntityNotFoundException("Can not "
                        + "find user with id" + requestDto.getFriendId()));

        friendShip.setUser(user);
        friendShip.setFriend(friend);
        friendShip.setStatus(FriendShip.Status.PENDING);
        friendShip.setCreatedAt(LocalDateTime.now());
        friendShipRepository.save(friendShip);
        return friendShipMapper.toDto(friendShip);
    }

    @Override
    public FriendShipResponseDto updateFriendShipStatus(
            Long id,
            UpdateFriendShipStatusDto statusDto) {
        FriendShip friendShip = friendShipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find "
                        + "friendShip with id: " + id));

        User authenticatedUser = getAuthenticatedUser();
        if (!friendShip.getUser().getId().equals(authenticatedUser.getId())
                && !friendShip.getFriend().getId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You can only update friendships you are part of.");
        }

        friendShip.setStatus(statusDto.getStatus());
        friendShipRepository.save(friendShip);
        return friendShipMapper.toDto(friendShip);
    }

    @Override
    public FriendShipResponseDto findFriendShipById(Long friendShipId) {
        FriendShip friendShip = friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new EntityNotFoundException("Can not find "
                        + "friendShip with id: " + friendShipId));

        return friendShipMapper.toDto(friendShip);
    }

    @Override
    public FriendShipResponseDto findFriendShipByFriendEmail(String email) {
        FriendShip friendShip = friendShipRepository.findFriendShipByFriendEmail(email)
                .orElseGet(() -> friendShipRepository.findFriendShipByUserEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("Can not find"
                                + " friendShip with email: " + email)));

        return friendShipMapper.toDto(friendShip);
    }

    @Transactional
    @Override
    public void deleteFriendShip(Long friendShipId) {
        FriendShip friendShip = friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new EntityNotFoundException("Can not "
                        + "find friendShip with id: " + friendShipId));

        User authenticatedUser = getAuthenticatedUser();
        if (!friendShip.getUser().getId().equals(authenticatedUser.getId())
                && !friendShip.getFriend().getId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You can only delete friendships you are part of.");
        }

        friendShipRepository.deleteById(friendShipId);
        friendShip.setDeleted(true);
    }

    @Override
    public List<FriendShipResponseDto> getAllFriendShips() {
        List<FriendShip>friendShips = friendShipRepository.findAll();
        return friendShips.stream()
                .map(friendShipMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public List<FriendShipResponseDto> getAllFriendShipsForCurrentUser() {
        User authenticatedUser = getAuthenticatedUser();
        List<FriendShip> friendShipsByUser = friendShipRepository
                .findByUserId(authenticatedUser.getId()).orElse(List.of());
        List<FriendShip> friendShipsByFriend = friendShipRepository
                .findByFriendId(authenticatedUser.getId()).orElse(List.of());

        List<FriendShip> allFriendShips = new ArrayList<>();
        allFriendShips.addAll(friendShipsByUser);
        allFriendShips.addAll(friendShipsByFriend);

        if (allFriendShips.isEmpty()) {
            throw new EntityNotFoundException("Can't find friends for this user");
        }

        return allFriendShips.stream()
                .map(friendShipMapper::toDto)
                .toList();
    }
}
