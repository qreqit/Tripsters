package com.example.tripsters.repository;

import com.example.tripsters.model.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {
    Optional<FriendShip> findFriendShipByFriendEmail(String email);

    Optional<FriendShip> findFriendShipByUserEmail(String email);

    Optional<List<FriendShip>> findByUserId(Long id);

    Optional<List<FriendShip>> findByFriendId(Long id);

    void deleteById(Long id);
}
