package com.mti825.sonia.models.enums;

public enum ContributionType {
    MATERIAL("Material", "Some material was given."),
    MONEY("Monetary", "Some money was given."),
    EQUIPMENT("Equipement", "A piece of equipement was given."),
    ENVIRONMENT("Environment", "An environment was loaned for use."),
    SERVICE("Service", "A certain service was granted.");

    private final String displayName;
    private final String description;

    ContributionType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
