package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ContributionDto;
import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.models.Contribution;
import com.mti825.sonia.repository.ContributionRepository;

@Service
public class ContributionService {
    @Autowired
    private ContributionRepository contributionRepository;

    /**     * Creates a new contribution.
     *
     * @param contributionDto the data transfer object containing contribution details
     * @return the created contribution response
     */
    public ContributionResponse createContribution(ContributionDto contributionDto) {
        Contribution contribution = createFromDto(contributionDto);
        contribution = contributionRepository.save(contribution);
        
        return new ContributionResponse(contribution);
    }

    /**     * Retrieves all contributions.
     *
     * This method returns a list of all contributions in the system.
     * It is useful for displaying all contributions on a page or in a list.
     * @return a list of ContributionRespnse objects representing all contributions
     */
    public List<ContributionResponse> getAllContributions() {
        List<Contribution> contributions = contributionRepository.findAll();

        return mapToResponseList(contributions);
    }

    /**     * Retrieves a contribution by its ID.
     *
     * @param id the ID of the contribution to retrieve
     * @return the ContributionResponse DTO
     * @throws IllegalArgumentException if no contribution is found with the given ID
     */
    public ContributionResponse getContributionById(Long id) {
        Contribution contribution = contributionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contribution not found with id " + id));

        return new ContributionResponse(contribution);
    }

    /**     * Deletes a contribution by its ID.
     *
     * This method will throw an IllegalArgumentException if no contribution is found with the given ID.
     * @param id the ID of the contribution to delete
     */
    public void deleteContributionById(Long id) {
        if (!contributionRepository.existsById(id)) {
            throw new IllegalArgumentException("Contribution not found with id: " + id);
        }
        contributionRepository.deleteById(id);
    }

    /**
     * Maps a ContributionDto to a Contribution entity.
     *
     * This method is used to convert the ContributionDt into a Contribution entity 
     * that can be saved in the database.
     * @param contributionDto the DTO to convert
     * @return Contribution entity for saving into the database
     */
    private Contribution createFromDto(ContributionDto contributionDto) {
        Contribution contribution = new Contribution(contributionDto);

        return contribution;
    }

    /**     * Maps a list of Contact entities to a list of ContactResponse DTOs.
     *
     * @param contacts the list of Contact entities to map
     * @return a list of ContactResponse DTOs
     */
    private List<ContributionResponse> mapToResponseList(List<Contribution> contributions) {
        return contributions.stream()
            .map(ContributionResponse::new)
            .toList();
    }
}
