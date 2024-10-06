package com.example.tripsters.controller;

import com.example.tripsters.dto.friendship.CreateFriendShipRequestDto;
import com.example.tripsters.dto.friendship.FriendShipResponseDto;
import com.example.tripsters.dto.friendship.UpdateFriendShipStatusDto;
import com.example.tripsters.service.FriendShipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendShipController {
    private final FriendShipService friendShipService;

    @PostMapping
    public FriendShipResponseDto addFriend(@RequestBody
                                               @Valid CreateFriendShipRequestDto requestDto) {
        return friendShipService.addFriend(requestDto);
    }

    @PutMapping("/{id}")
    public FriendShipResponseDto updateFriendShipStatus(@PathVariable Long id, @RequestBody
            UpdateFriendShipStatusDto statusDto) {
        return friendShipService.updateFriendShipStatus(id, statusDto);
    }

    @GetMapping("/id/{findFriendId}")
    public FriendShipResponseDto findFriendShipById(@PathVariable Long findFriendId) {
        return friendShipService.findFriendShipById(findFriendId);
    }

    @GetMapping("/email/{friendEmail}")
    public FriendShipResponseDto findFriendShipByFriendEmail(@PathVariable String friendEmail) {
        return friendShipService.findFriendShipByFriendEmail(friendEmail);
    }

    @GetMapping
    public List<FriendShipResponseDto> getAllFriendShips() {
        return friendShipService.getAllFriendShips();
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriendById(@PathVariable Long friendId) {
        friendShipService.deleteFriendShip(friendId);
    }
}
