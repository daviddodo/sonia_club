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

    public SponsorResponse getSponsorById(Long id) {
        return sponsorRepository.findById(id)
            .map(sponsor -> new SponsorResponse(
                sponsor.getId(),
                sponsor.getName(),
                sponsor.getDescription()
            ))
            .orElseThrow(() -> new IllegalArgumentException("Sponsor not found with id: " + id));
    }

    public List<SponsorResponse> getSponsorByNameContainingString(String partialName) {
        return sponsorRepository.findByNameContainingIgnoreCase(partialName).stream()
            .map(sponsor -> new SponsorResponse(
                sponsor.getId(),
                sponsor.getName(),
                sponsor.getDescription()
            )).toList();
    }

    public void deleteSponsorById(Long id) {
        if (!sponsorRepository.existsById(id)) {
            throw new IllegalArgumentException("Sponsor not found with id: " + id);
        }
        sponsorRepository.deleteById(id);
    }
}
