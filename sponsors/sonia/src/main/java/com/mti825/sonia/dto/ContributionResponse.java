package com.mti825.sonia.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mti825.sonia.models.Contribution;
import com.mti825.sonia.models.enums.ClubDepartment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContributionResponse {
    private Long id;
    private String donation;
    private String description;
    private BigDecimal monetaryValue;
    private String temporalValue;
    private Date date;
    private String contributionType;
    private List<String> clubDepartments;
    private String contactName;
    private String clubRepName;

    public ContributionResponse(Contribution contribution) {
        id = contribution.getId();
        donation = contribution.getDonation();
        description = contribution.getDescription();
        monetaryValue = contribution.getMonetaryValue();
        temporalValue = contribution.getTemporalValue();
        date = contribution.getDate();
        contributionType = contribution.getContributionType().getDisplayName();

        if (contribution.getClubDepartments() != null) {
            clubDepartments = contribution.getClubDepartments()
                .stream()
                .map(ClubDepartment::getDisplayName)
                .toList();
        } else {
            clubDepartments = List.of();
        }

        contactName = contribution.getContact().getFullName();
        clubRepName = contribution.getClubRep() != null ? contribution.getClubRep().getFullName() : "";
    }
}
