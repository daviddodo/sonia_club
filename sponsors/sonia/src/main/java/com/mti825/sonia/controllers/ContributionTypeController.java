package com.mti825.sonia.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.models.enums.ContributionType;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/contribution-types")
public class ContributionTypeController {
    /**     * Retrieves all contribution types.
     *
     * This method returns a list of all contribution types in the system.
     * It is useful for displaying all contribution types in a dropdown.
     * @return a list of ContributionType objects representing all contribution types
     */
    @GetMapping
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
