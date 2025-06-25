package com.mti825.sonia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private Long id;
    private String fname;
    private String lname;
    private String role;
    private String email;
    private String phone;
    private Long sponsorId;
}
