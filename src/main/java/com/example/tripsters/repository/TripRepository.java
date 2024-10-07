package com.example.tripsters.repository;

import com.example.tripsters.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Optional<List<Trip>> findByUsersId(Long id);
}
