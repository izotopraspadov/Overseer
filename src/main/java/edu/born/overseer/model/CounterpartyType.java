package edu.born.overseer.model;

public enum CounterpartyType {
    COMPANY("Компания"),
    EMPLOYEE("Сотрудник");

    private String description;

    CounterpartyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
