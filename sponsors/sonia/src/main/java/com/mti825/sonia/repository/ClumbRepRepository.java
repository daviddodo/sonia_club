package com.mti825.sonia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mti825.sonia.models.ClubRep;
import com.mti825.sonia.models.Contact;

public interface ClumbRepRepository extends JpaRepository<ClubRep, Long> {
    List<Contact> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(String fname, String lname);
}
