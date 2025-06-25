package com.mti825.sonia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findBySponsorId(Long sponsorId);
    List<Contact> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(String fname, String lname);
}
