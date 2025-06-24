package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.SponsorDto;
import com.mti825.sonia.dto.SponsorResponse;
import com.mti825.sonia.models.Sponsor;
import com.mti825.sonia.repository.SponsorRepository;

@Service
public class SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;

    public SponsorResponse createSponsor(SponsorDto sponsorDto) {
        Sponsor sponsor = new Sponsor(
            sponsorDto.getName(),
            sponsorDto.getDescription()
        );
            
        sponsor = sponsorRepository.save(sponsor);

        return new SponsorResponse(
            sponsor.getId(),
            sponsor.getName(),
            sponsor.getDescription()
        );
    }

    public List<SponsorResponse> getAllSponsors() {
        return sponsorRepository.findAll().stream()
            .map(sponsor -> new SponsorResponse(
                sponsor.getId(),
                sponsor.getName(),
                sponsor.getDescription()
            ))
            .toList();
    }

    public SponsorResponse getSponsorByName(String name) {
        Sponsor sponsor = sponsorRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Sponsor not found with name: " + name));
        
        return new SponsorResponse(
            sponsor.getId(),
            sponsor.getName(),
            sponsor.getDescription()
        );
    }
}
