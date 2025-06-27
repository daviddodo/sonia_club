package com.mti825.sonia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.Contribution;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    
}
