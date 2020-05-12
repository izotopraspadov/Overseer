package edu.born.overseer.model;

import edu.born.overseer.annotation.PhoneNumber;
import edu.born.overseer.model.abstraction.AbstractContactEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phones", uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "phone_unique_idx")})
@NamedQueries({
        @NamedQuery(name = "Phone:allByEmployee",
                query = "SELECT p FROM Phone p WHERE p.employee.id=:ownerId ORDER BY p.number"),
        @NamedQuery(name = "Phone:allBContactPerson",
                query = "SELECT p FROM Phone p WHERE p.contactPerson.id=:ownerId ORDER BY p.number")
})
public class Phone extends AbstractContactEntity {

    @PhoneNumber
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    public Phone() {
    }

    /**
     * Cloning constructor
     **/

    public Phone(Phone other) {
        super(other.getId(), other.getContactPerson(), other.getEmployee(), other.getOwnerType());
        this.number = other.getNumber();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Fluent API
     **/

    public Phone id(Integer id) {
        this.id = id;
        return this;
    }

    public Phone contactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public Phone employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Phone ownerType(OwnerType ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public Phone number(String number) {
        this.number = number;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Phone otherPhone = (Phone) other;
        return number.equals(otherPhone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id + ", " +
                "number='" + number + ", " +
                "ownerType=" + ownerType +
                "}\n";
    }

}
