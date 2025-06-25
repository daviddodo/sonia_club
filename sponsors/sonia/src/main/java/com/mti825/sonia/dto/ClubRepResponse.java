package com.mti825.sonia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubRepResponse {
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
}
