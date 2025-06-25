package com.mti825.sonia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.dto.ContributionTypeDto;
import com.mti825.sonia.dto.ContributionTypeResponse;
import com.mti825.sonia.services.ContributionTypeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/contribution-types")
public class ContributionTypeController {
    @Autowired
    private ContributionTypeService contributionTypeService;

    /**     * Creates a new contribution type.
     *
     * @param contributionTypeDto the data transfer object containing contribution type details
     * @return the created contribution type response
     */
    @PostMapping()
    public ContributionTypeResponse createContributionType(@RequestBody ContributionTypeDto contributionTypeDto) {
        return contributionTypeService.createContributionType(contributionTypeDto);
    }
    
    /**     * Retrieves all contribution types.
     *
     * This method returns a list of all contribution types in the system.
     * It is useful for displaying all contribution types in a list.
     * @return a list of ContributionTypeResponse objects representing all contribution types
     */
    @GetMapping()
    public List<ContributionTypeResponse> getAllContributionTypes() {
        return contributionTypeService.getAllContributionTypes();
    }
}
