package edu.guap.enclave.model;

public enum TypeCompany {
    OUR("Наше"),
    CUSTOMER("Заказчик"),
    OTHER("Остальные");

    private String description;

    TypeCompany(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
