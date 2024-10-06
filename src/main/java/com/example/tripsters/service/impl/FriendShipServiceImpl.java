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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendShipServiceImpl implements FriendShipService {
    private final UserRepository userRepository;
    private final FriendShipRepository friendShipRepository;
    private final FriendShipMapper friendShipMapper;

    @Override
    public FriendShipResponseDto addFriend(CreateFriendShipRequestDto requestDto) {
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
    public FriendShipResponseDto findFriendShipByFriendEmail(String friendEmail) {
        FriendShip friendShip = friendShipRepository.findFriendShipByUserEmail(friendEmail)
                .orElseThrow(() -> new EntityNotFoundException("Can not found friendShip"
                        + " by email " + friendEmail));

        return friendShipMapper.toDto(friendShip);
    }

    @Override
    public void deleteFriendShip(Long friendShipId) {
        friendShipRepository.deleteById(friendShipId);
    }
}
