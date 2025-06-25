package com.mti825.sonia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    @NotBlank(message = "Project name cannot be blank")
    @Size(max = 50, message = "Project name cannot exceed 50 characters")
    private String name;

    @Size(max = 255, message = "Project description cannot exceed 255 characters")
    private String description;
}
