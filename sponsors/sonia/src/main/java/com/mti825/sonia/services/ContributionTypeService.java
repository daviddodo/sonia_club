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

    public ContributionTypeResponse createContributionType(ContributionTypeDto contributionTypeDto) {
        ContributionType contributionType = new ContributionType(
            contributionTypeDto.getName(),
            contributionTypeDto.getDescription()
        );

        contributionType = contributionTypeRepository.save(contributionType);

        return mapToResponse(contributionType);
    }

    public List<ContributionTypeResponse> getAllContributionTypes() {
        List<ContributionType> contributionTypes = contributionTypeRepository.findAll();

        return mapToResponseList(contributionTypes);
    }

    private ContributionTypeResponse mapToResponse(ContributionType contributionType) {
        return new ContributionTypeResponse(
            contributionType.getId(),
            contributionType.getName(),
            contributionType.getDescription()
        );
    }

    private List<ContributionTypeResponse> mapToResponseList(List<ContributionType> contributionTypes) {
        return contributionTypes.stream()
            .map(this::mapToResponse)
            .toList();
    }
}
