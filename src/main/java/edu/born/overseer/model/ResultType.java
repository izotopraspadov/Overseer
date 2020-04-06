package edu.born.overseer.model;

public enum ResultType {
    COMPLETED("Выполнено"),
    NOT_COMPLETED("Не выполнено"),
    PARTIALLY_COMPLETED("Частично выполнено");

    private String description;

    ResultType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}