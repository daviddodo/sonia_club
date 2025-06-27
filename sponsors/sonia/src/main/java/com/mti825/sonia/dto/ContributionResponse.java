package com.mti825.sonia.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.mti825.sonia.models.Contribution;

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

    public ContributionResponse(Contribution contribution) {
        id = contribution.getId();
        donation = contribution.getDonation();
        description = contribution.getDescription();
        monetaryValue = contribution.getMonetaryValue();
        temporalValue = contribution.getTemporalValue();
        date = contribution.getDate();
    }
}
