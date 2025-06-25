package com.mti825.sonia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    @NotBlank
    @Size(max = 50)
    private String fname;

    @NotBlank
    @Size(max = 50)
    private String lname;

    @NotBlank
    @Size(max = 50)
    private String role;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Long sponsorId;
}
