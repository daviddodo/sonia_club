package com.mti825.sonia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(max=255, message = "Name cannot exceed 255 characters")
    private String name;

    @Size(max=500, message = "Description cannot exceed 500 characters")
    private String description;
}
