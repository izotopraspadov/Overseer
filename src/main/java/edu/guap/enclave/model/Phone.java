package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractContactEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phones",
        uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "phone_unique_idx")})
public class Phone extends AbstractContactEntity {

    @Column(name = "number", nullable = false, unique = true)
    @NotBlank
    @Size(min = 15, max = 15)
    private String number;

    public Phone() {
    }

    public Phone(Integer id, ContactPerson contactPerson, Employee employee, TypeEmailOwner typeOwner, String number) {
        super(id, contactPerson, employee, typeOwner);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
