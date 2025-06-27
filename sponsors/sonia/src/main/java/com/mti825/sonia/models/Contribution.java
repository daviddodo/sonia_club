package com.mti825.sonia.models;

import java.math.BigDecimal;
import java.util.Date;

import com.mti825.sonia.dto.ContributionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contributions")
@Data
@NoArgsConstructor
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donation;
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal monetaryValue;
    private String temporalValue;
    private Date date;

    public Contribution(ContributionDto contributionDto) {
        donation = contributionDto.getDonation();
        description = contributionDto.getDescription();
        monetaryValue = contributionDto.getMonetaryValue();
        temporalValue = contributionDto.getTemporalValue();
        date = contributionDto.getDate();
    }
}
