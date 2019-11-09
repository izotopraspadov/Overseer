package edu.guap.enclave.model;

public enum Reliability {
    LOW("Низкий"),
    MIDDLE("Средний"),
    HIGH("Высокий");

    private String description;

    Reliability(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
