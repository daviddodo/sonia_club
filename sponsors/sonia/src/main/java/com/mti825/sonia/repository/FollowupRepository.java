package com.mti825.sonia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.Followup;

public interface FollowupRepository extends JpaRepository<Followup, Long> {
    
}
