package com.mti825.sonia.models;

import java.util.Date;

import com.mti825.sonia.dto.FollowupDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "followups")
@Data
@NoArgsConstructor
public class Followup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Boolean active;
    private Date date;

    public Followup(FollowupDto followupDto) {
        description = followupDto.getDescription();
        active = followupDto.getActive();
        date = followupDto.getDate();
    }
}
