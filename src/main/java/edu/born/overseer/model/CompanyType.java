package edu.born.overseer.model;

public enum CompanyType {
    OUR("Наше"),
    CUSTOMER("Заказчик"),
    OTHER("Остальные");

    private String description;

    CompanyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
