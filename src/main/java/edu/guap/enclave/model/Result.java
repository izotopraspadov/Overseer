package edu.guap.enclave.model;

public enum Result {
    COMPLETED("Выполнено"),
    NOT_COMPLETED("Не выполнено"),
    PARTIALLY_COMPLETED("Частично выполнено");

    private String description;

    Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}