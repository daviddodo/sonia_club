package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ClubRepDto;
import com.mti825.sonia.dto.ClubRepResponse;
import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.services.ClubRepService;
import com.mti825.sonia.services.ContributionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/club-reps")
public class ClubRepController {
    @Autowired
    private ClubRepService clubRepService;

    @Autowired
    private ContributionService contributionService;

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
    
    /**     * Retrieves a ClubRep by its ID.
     *
     * @param id the ID of the club rep to retrieve
     * @return the ClubRepResponse DTO
     * @throws IllegalArgumentException if no club rep is found with the given ID
     */
    @GetMapping(value="/{id}")
    public ClubRepResponse getClubRepById(@PathVariable Long id) {
        return clubRepService.getClubRepById(id);
    }
    
    /**     * Retrieves club reps by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of ClubRep objects matching the partial name
     */
    @GetMapping("/search")
    public List<ClubRepResponse> getClubRepByPartialName(@RequestParam String name) {
        return clubRepService.getClubRepByPartialName(name);
    }
    
    /**     * Deletes a club rep by its ID.
     *
     * @param id the ID of the club rep to delete
     * @throws IllegalArgumentException if no club rep is found with the given ID
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClubRepByid(@PathVariable Long id) {
        clubRepService.deleteClubRepByid(id);
    }

    /**     * Retrieves contributions associated with a club rep by its ID.
     *
     * @param id the ID of the club rep
     * @return a list of ContributionResponse objects associated with the club rep
     */
    @GetMapping(value="/{id}/contributions")
    public List<ContributionResponse> getContributionsByClubRepId(@PathVariable Long id) {
        return contributionService.getContributionsByClubRepId(id);
    }
}
