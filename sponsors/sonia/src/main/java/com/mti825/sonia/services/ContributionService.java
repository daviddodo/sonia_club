package com.mti825.sonia.services;

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
}
