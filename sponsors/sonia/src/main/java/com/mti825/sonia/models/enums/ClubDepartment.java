package com.mti825.sonia.models.enums;

public enum ClubDepartment {
    ALUMNI("Alumni Relations"),
    ELECTRICAL("Electrical Engineering"),
    GENERAL("General"),
    MECHANICAL("Mechanical Engineering"),
    SOFTWARE("Software Engineering");

    private final String displayName;

    ClubDepartment(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
