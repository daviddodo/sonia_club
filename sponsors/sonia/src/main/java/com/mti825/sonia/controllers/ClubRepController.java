package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ClubRepDto;
import com.mti825.sonia.dto.ClubRepResponse;
import com.mti825.sonia.services.ClubRepService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/club-reps")
public class ClubRepController {
    @Autowired
    private ClubRepService clubRepService;

    /**     * Creates a new club rep.
     *
     * @param clubRepDto the data transfer object containing club rep details
     * @return the created ClubRepResponse
     */
    @PostMapping
    public ClubRepResponse createClubRep(@RequestBody @Valid ClubRepDto clubRepDto) {
        return clubRepService.createClubRep(clubRepDto);
    }
    
    /**     * Retrieves all club reps.
     *
     * This method returns a list of all club reps in the system.
     * It is useful for displaying all club reps on a page or in a list.
     * @return a list of ClubRepResponse objects representing all club reps
     */
    @GetMapping
    public List<ClubRepResponse> getAllClubReps() {
        return clubRepService.getAllClubReps();
    }
    
}
