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
