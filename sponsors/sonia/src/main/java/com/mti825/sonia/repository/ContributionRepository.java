package com.mti825.sonia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.Contribution;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    List<Contribution> findByContactId(Long contactId);
    List<Contribution> findByContactIdIn(List<Long> contactIds);
    List<Contribution> findByClubRepId(Long clubRepId);
    List<Contribution> findByProjectId(Long projectId);
}
