package com.mti825.sonia.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mti825.sonia.dto.ContributionDto;
import com.mti825.sonia.models.enums.ClubDepartment;
import com.mti825.sonia.models.enums.ContributionType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "contributionType", nullable = false)
    private ContributionType contributionType;

    @ElementCollection(targetClass = ClubDepartment.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "contribution_helped_depts", joinColumns = @JoinColumn(name = "contribution_id"))
    private Set<ClubDepartment> clubDepartments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "club_rep_id", nullable = true)
    private ClubRep clubRep;

    public Contribution(ContributionDto contributionDto) {
        donation = contributionDto.getDonation();
        description = contributionDto.getDescription();
        monetaryValue = contributionDto.getMonetaryValue();
        temporalValue = contributionDto.getTemporalValue();
        date = contributionDto.getDate();
        contributionType = contributionDto.getContributionType();
        clubDepartments = contributionDto.getClubDepartments() != null
                  ? contributionDto.getClubDepartments()
                  : new HashSet<>();
    }
}
