package com.mti825.sonia.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mti825.sonia.models.enums.ClubDepartment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("club-departments")
public class ClubDepartmentController {
    /**     * Retrieves all club departments.
     *
     * This method returns a list of all club departments in the system.
     * It is useful for displaying all club departments in a dropdown.
     * @return a list of ClubDepartment objects representing all club departments
     */
    @GetMapping
    public List<Map<String, String>> getAllClubDepartments() {
        return Arrays.stream(ClubDepartment.values())
            .map(dept -> Map.of(
                "key", dept.name(),
                "label", dept.getDisplayName()
            ))
            .toList();
    }
}
