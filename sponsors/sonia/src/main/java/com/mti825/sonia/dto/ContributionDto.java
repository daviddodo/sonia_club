package com.mti825.sonia.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.mti825.sonia.models.enums.ClubDepartment;
import com.mti825.sonia.models.enums.ContributionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionDto {
    @NotBlank(message = "Donation cannot be blank")
    @Size(max = 50, message = "Donation cannot exceed 50 characters")
    private String donation;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @PositiveOrZero(message = "Monetary value of contribution must be at the very least 0")
    private BigDecimal monetaryValue;

    @Size(max = 50, message = "Temporal value of contribution cannot exceed 50 characters")
    private String temporalValue;

    @NotNull(message = "Date cannot be null")
    private Date date;

    @NotNull(message = "Contribution type cannot be null")
    private ContributionType contributionType;

    private Set<ClubDepartment> clubDepartments;
}
