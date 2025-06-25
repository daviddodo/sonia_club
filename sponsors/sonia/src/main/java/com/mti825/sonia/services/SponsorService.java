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

    /**
     * Creates a new sponsor.
     *
     * @param sponsorDto the data transfer object containing sponsor details
     * @return the created sponsor response
     */
    public SponsorResponse createSponsor(SponsorDto sponsorDto) {
        Sponsor sponsor = new Sponsor(
            sponsorDto.getName(),
            sponsorDto.getDescription()
        );
            
        sponsor = sponsorRepository.save(sponsor);

        return mapToResponse(sponsor);
    }

    /**
     * Retrieves all sponsors.
     *
     * This method returns a list of all sponsors in the system.
     * It is useful for displaying all sponsors on a page or in a list.
     * @return a list of SponsorResponse objects representing all sponsors
     */
    public List<SponsorResponse> getAllSponsors() {
        List<Sponsor> sponsors = sponsorRepository.findAll();

        return mapToResponseList(sponsors);
    }

    /**
     * Retrieves a sponsor by its ID.
     *
     * @param id the ID of the sponsor to retrieve
     * @return the sponsor response
     * @throws IllegalArgumentException if no sponsor is found with the given ID
     */
    public SponsorResponse getSponsorById(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sponsor not found with id: " + id));
        
        return mapToResponse(sponsor);
    }

    /**
     * Retrieves a list of sponsors whose names contain the specified string, ignoring case.
     *
     * This method is useful for searching sponsors by partial names.
     * @param partialName
     * @return
     */
    public List<SponsorResponse> getSponsorByNameContainingString(String partialName) {
        List<Sponsor> sponsors = sponsorRepository.findByNameContainingIgnoreCase(partialName);

        return mapToResponseList(sponsors);
    }

    /**
     * Deletes a sponsor by its ID.
     *
     * This method will throw an IllegalArgumentException if no sponsor is found with the given ID.
     * @param id
     */
    public void deleteSponsorById(Long id) {
        if (!sponsorRepository.existsById(id)) {
            throw new IllegalArgumentException("Sponsor not found with id: " + id);
        }
        sponsorRepository.deleteById(id);
    }

    /**
     * Maps a Sponsor entity to a SponsorResponse DTO.
     *
     * This method is used to convert the Sponsor entity retrieved from the database into a response DTO
     * that can be returned to the client.
     * @param sponsor
     * @return
     */
    private SponsorResponse mapToResponse(Sponsor sponsor) {
        return new SponsorResponse(
            sponsor.getId(),
            sponsor.getName(),
            sponsor.getDescription()
        );
    }

    /**
     * Maps a list of Sponsor entities to a list of SponsorResponse DTOs.
     *
     * This method is used to convert a list of Sponsor entities retrieved from the database into a list of
     * response DTOs that can be returned to the client.
     * @param sponsors
     * @return
     */
    private List<SponsorResponse> mapToResponseList(List<Sponsor> sponsors) {
        return sponsors.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
