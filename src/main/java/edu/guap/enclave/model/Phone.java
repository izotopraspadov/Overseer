package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractContactEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phones",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "number", name = "phone_unique_idx")
        })
@NamedQueries({
        @NamedQuery(name = Phone.ALL_BY_EMPLOYEE, query = "SELECT p FROM Phone p WHERE p.employee.id=:ownerId ORDER BY p.number"),
        @NamedQuery(name = Phone.ALL_BY_CONTACT_PERSON, query = "SELECT p FROM Phone p WHERE p.contactPerson.id=:ownerId ORDER BY p.number")
})
public class Phone extends AbstractContactEntity {

    public static final String ALL_BY_EMPLOYEE = "Phone.GetAllByEmployee";
    public static final String ALL_BY_CONTACT_PERSON = "Phone.GetAllBContactPerson";

    @Column(name = "number", nullable = false, unique = true)
    @NotBlank
    @Size(min = 11, max = 11)
    private String number;

    public Phone() {
    }

    public Phone(Integer id, ContactPerson contactPerson, Employee employee, TypeOwner typeOwner, String number) {
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
