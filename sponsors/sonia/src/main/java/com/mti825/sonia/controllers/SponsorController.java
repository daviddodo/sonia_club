package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.SponsorDto;
import com.mti825.sonia.dto.SponsorResponse;
import com.mti825.sonia.services.SponsorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sponsors")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public SponsorResponse createSponsor(@RequestBody SponsorDto sponsorDto) {
        return sponsorService.createSponsor(sponsorDto);
    }
    
    @GetMapping("getAll")
    public List<SponsorResponse> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }

    @GetMapping("getByName")
    public SponsorResponse getSponsorById(@RequestParam String name) {
        return sponsorService.getSponsorByName(name);
    }
}
