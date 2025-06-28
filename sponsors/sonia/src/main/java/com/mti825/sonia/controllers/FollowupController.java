package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.FollowupDto;
import com.mti825.sonia.dto.FollowupResponse;
import com.mti825.sonia.services.FollowupService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/followups")
public class FollowupController {
    @Autowired
    FollowupService followupService;

    /**     * Creates a new followup.
     *
     * @param followupDto the data transfer object containing followup details
     * @return the created followup response
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FollowupResponse createFollowup(@RequestBody @Valid FollowupDto followupDto) {
        return followupService.createFollowup(followupDto);
    }
    
    /**     * Retrieves all followups.
     *
     * This method returns a list of all followups in the system.
     * It is useful for displaying all followups on a page or in a list.
     * @return a list of FollowupResponse objects representing all followups
     */
    @GetMapping
    public List<FollowupResponse> getAllFollowups() {
        return followupService.getAllFollowups();
    }

    /**     * Retrieves a followup by its ID.
     *
     * @param id the ID of the followup to retrieve
     * @return the FollowupResponse DTO
     * @throws IllegalArgumentException if no followup is found with the given ID
     */
    @GetMapping(value="/{id}")
    public FollowupResponse getFollowupById(@PathVariable Long id) {
        return followupService.getFollowupById(id);
    }
    

    /**     * Deletes a followup by its ID.
     *
     * This method will throw an IllegalArgumentException if no followup is found with the given ID.
     * @param id the ID of the followup to delete
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollowupById(@PathVariable Long id) {
        followupService.deleteFollowupById(id);
    }
}
