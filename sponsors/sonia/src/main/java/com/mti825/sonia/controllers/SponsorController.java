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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SponsorResponse createSponsor(@RequestBody @Valid SponsorDto sponsorDto) {
        return sponsorService.createSponsor(sponsorDto);
    }
    
    @GetMapping()
    public List<SponsorResponse> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }

    @GetMapping("/search")
    public List<SponsorResponse> getSponsorByPartialName(@RequestParam String name) {
        return sponsorService.getSponsorByNameContainingString(name);
    }

    @GetMapping(value="/{id}")
    public SponsorResponse getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsorById(id);
    }

    @GetMapping("/{id}/contacts")
    public List<ContactResponse> getContactsBySponsorId(@PathVariable Long id) {
        return contactService.getContactsBySponsorId(id);
    }

    // @GetMapping("/{id}/contributions")
    // public List<ContributionResponse> getContributionsBySponsorId(@PathVariable Long id) {
    //     return contributionService.getContributionsBySponsorId(id);
    // }
}
