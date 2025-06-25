package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContactResponse;
import com.mti825.sonia.dto.SponsorDto;
import com.mti825.sonia.dto.SponsorResponse;
import com.mti825.sonia.services.ContactService;
import com.mti825.sonia.services.SponsorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sponsors")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ContactService contactService;

    /**
     * Creates a new sponsor.
     *
     * @param sponsorDto the data transfer object containing sponsor details
     * @return the created sponsor response
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SponsorResponse createSponsor(@RequestBody @Valid SponsorDto sponsorDto) {
        return sponsorService.createSponsor(sponsorDto);
    }
    
    /**
     * Retrieves all sponsors.
     *
     * This method returns a list of all sponsors in the system.
     * It is useful for displaying all sponsors on a page or in a list.
     * @return a list of SponsorResponse objects representing all sponsors
     */
    @GetMapping()
    public List<SponsorResponse> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }

    /**     * Retrieves sponsors by a partial name match.
     *
     * @param name the partial name to search for
     * @return a list of SponsorResponse objects matching the partial name
     */
    @GetMapping("/search")
    public List<SponsorResponse> getSponsorByPartialName(@RequestParam String name) {
        return sponsorService.getSponsorByNameContainingString(name);
    }

    /**     * Retrieves a sponsor by its ID.
     *
     * @param id the ID of the sponsor to retrieve
     * @return the sponsor response
     * @throws IllegalArgumentException if no sponsor is found with the given ID
     */
    @GetMapping(value="/{id}")
    public SponsorResponse getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id);
    }

    /**     * Deletes a sponsor by its ID.
     *
     * @param id the ID of the sponsor to delete
     * @throws IllegalArgumentException if no sponsor is found with the given ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSponsorById(@PathVariable Long id) {
        sponsorService.deleteSponsorById(id);
    }

    /**     * Retrieves contacts associated with a sponsor by its ID.
     *
     * @param id the ID of the sponsor
     * @return a list of ContactResponse objects associated with the sponsor
     */
    @GetMapping("/{id}/contacts")
    public List<ContactResponse> getContactsBySponsorId(@PathVariable Long id) {
        return contactService.getContactsBySponsorId(id);
    }

    // @GetMapping("/{id}/contributions")
    // public List<ContributionResponse> getContributionsBySponsorId(@PathVariable Long id) {
    //     return contributionService.getContributionsBySponsorId(id);
    // }
}
