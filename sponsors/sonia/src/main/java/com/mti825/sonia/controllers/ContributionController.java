package com.mti825.sonia.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContributionDto;
import com.mti825.sonia.dto.ContributionResponse;
import com.mti825.sonia.models.enums.ContributionType;
import com.mti825.sonia.services.ContributionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/contributions")
public class ContributionController {
    @Autowired
    ContributionService contributionService;

    /**     * Creates a new contribution.
     *
     * @param contributionDto the data transfer object containing contribution details
     * @return the created contribution response
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContributionResponse createContribution(@RequestBody @Valid ContributionDto contributionDto) {
        return contributionService.createContribution(contributionDto);
    }

    /**     * Retrieves all contacts.
     *
     * This method returns a list of all contacts in the system.
     * It is useful for displaying all contacts on a page or in a list.
     * @return a list of ContactResponse objects representing all contacts
     */
    @GetMapping
    public List<ContributionResponse> getAllContributions() {
        return contributionService.getAllContributions();
    }

    /**     * Retrieves a contribution by its ID.
     *
     * @param id the ID of the contribution to retrieve
     * @return the ContributionResponse DTO
     * @throws IllegalArgumentException if no contribution is found with the given ID
     */
    @GetMapping(value = "/{id}")
    public ContributionResponse getContributionById(@PathVariable Long id) {
        return contributionService.getContributionById(id);
    }
    
    /**     * Deletes a contribution by its ID.
     *
     * This method will throw an IllegalArgumentException if no contribution is found with the given ID.
     * @param id the ID of the contribution to delete
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContributionById(@PathVariable Long id) {
        contributionService.deleteContributionById(id);
    }

    /**     * Retrieves all contribution types.
     *
     * This method returns a list of all contribution types in the system.
     * It is useful for displaying all contribution types in a dropdown.
     * @return a list of ContributionType objects representing all contribution types
     */
    @GetMapping("/types")
    public List<Map<String, String>> getAll() {
        return Arrays.stream(ContributionType.values())
            .map(type -> Map.of(
                "key", type.name(),
                "label", type.getDisplayName(),
                "description", type.getDescription()
            ))
            .toList();
    }
}
