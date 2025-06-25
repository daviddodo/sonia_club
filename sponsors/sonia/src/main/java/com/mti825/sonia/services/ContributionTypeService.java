package com.mti825.sonia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mti825.sonia.dto.ContributionTypeDto;
import com.mti825.sonia.dto.ContributionTypeResponse;
import com.mti825.sonia.models.ContributionType;
import com.mti825.sonia.repository.ContributionTypeRepository;

@Service
public class ContributionTypeService {
    @Autowired
    private ContributionTypeRepository contributionTypeRepository;

    /**     * Creates a new contribution type.
     *
     * @param contributionTypeDto the data transfer object containing contribution type details
     * @return the created contribution type response
     */
    public ContributionTypeResponse createContributionType(ContributionTypeDto contributionTypeDto) {
        ContributionType contributionType = new ContributionType(
            contributionTypeDto.getName(),
            contributionTypeDto.getDescription()
        );

        contributionType = contributionTypeRepository.save(contributionType);

        return mapToResponse(contributionType);
    }

    /**     * Retrieves all contribution types.
     *
     * This method returns a list of all contribution types in the system.
     * It is useful for displaying all contribution types on a page or in a list.
     * @return a list of ContributionTypeResponse objects representing all contribution types
     */
    public List<ContributionTypeResponse> getAllContributionTypes() {
        List<ContributionType> contributionTypes = contributionTypeRepository.findAll();

        return mapToResponseList(contributionTypes);
    }

    /**     * Deletes a contribution type by its ID.
     *
     * This method will throw an IllegalArgumentException if no contribution type is found with the given ID.
     * @param id the ID of the contribution type to delete
     */
    public void deleteContributionTypeById(Long id) {
        if (!contributionTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Contribution type not found with id: " + id);
        }
        contributionTypeRepository.deleteById(id);
    }

    /**     * Maps a ContributionType entity to a ContributionResponse DTO.
     *
     * @param contributionTypes the ContributionType entity to map
     * @return the ContributionResponse DTO
     */
    private ContributionTypeResponse mapToResponse(ContributionType contributionType) {
        return new ContributionTypeResponse(
            contributionType.getId(),
            contributionType.getName(),
            contributionType.getDescription()
        );
    }

    /**     * Maps a list of ContributionType entities to a list of ContributionResponse DTOs.
     *
     * @param contributionTypes the list of ContributionType entities to map
     * @return a list of ContributionResponse DTOs
     */
    private List<ContributionTypeResponse> mapToResponseList(List<ContributionType> contributionTypes) {
        return contributionTypes.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
