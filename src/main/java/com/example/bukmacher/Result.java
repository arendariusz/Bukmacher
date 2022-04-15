package com.example.bukmacher;

public enum Result {
    HOME("Gospodarze"), DRAW("Remis"), AWAY("Goście");

    private final String description;

    private Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
