package com.example.tripsters.repository;

import com.example.tripsters.model.MapPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapPointRepository extends JpaRepository<MapPoint, Long> {
    Optional<MapPoint.PointType> findByType(String type);
}
