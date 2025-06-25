package com.mti825.sonia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.ClubRep;

public interface ClubRepRepository extends JpaRepository<ClubRep, Long> {
    List<ClubRep> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(String fname, String lname);
}
