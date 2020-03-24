package edu.born.overseer.model;

public enum TypeCounterparty {
    COMPANY("Компания"),
    EMPLOYEE("Сотрудник");

    private String description;

    TypeCounterparty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}