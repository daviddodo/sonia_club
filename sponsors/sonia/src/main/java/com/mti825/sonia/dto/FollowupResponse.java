package com.mti825.sonia.dto;

import java.util.Date;

import com.mti825.sonia.models.Followup;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowupResponse {
    private Long id;
    private String description;
    private Boolean active;
    private Date date;
    private String contribution;

    public FollowupResponse(Followup followup) {
        id = followup.getId();
        description = followup.getDescription();
        active = followup.getActive();
        date = followup.getDate();
        contribution = followup.getContribution().getDonation();
    }
}
