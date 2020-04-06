package edu.born.overseer.model;

public enum ReliabilityType {
    LOW("Низкий"),
    MIDDLE("Средний"),
    HIGH("Высокий");

    private String description;

    ReliabilityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
