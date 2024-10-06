package com.example.tripsters.repository;

import com.example.tripsters.model.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {
    Optional<FriendShip> findFriendShipByFriendEmail(String email);
}
