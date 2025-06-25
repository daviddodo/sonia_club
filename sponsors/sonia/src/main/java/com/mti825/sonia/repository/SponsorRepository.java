package com.mti825.sonia.repository;

import com.mti825.sonia.models.Sponsor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    List<Sponsor> findByNameContainingIgnoreCase(String name);
}
