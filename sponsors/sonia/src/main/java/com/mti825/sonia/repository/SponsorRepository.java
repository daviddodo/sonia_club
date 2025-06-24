package com.mti825.sonia.repository;

import com.mti825.sonia.models.Sponsor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    Optional<Sponsor> findByName(String name);
}
