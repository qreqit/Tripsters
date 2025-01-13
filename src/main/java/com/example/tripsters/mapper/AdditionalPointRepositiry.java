package com.example.tripsters.mapper;

import com.example.tripsters.model.AdditionalPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalPointRepositiry extends JpaRepository<AdditionalPoint, Long> {
}
