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

    @Column(name = "number", nullable = false, unique = true)
    @PhoneNumber
    private String number;

    public Phone() {
    }

    public Phone(Integer id, OwnerType ownerType, String number) {
        super(id, null, null, ownerType);
        this.number = number;
    }

    public Phone(Integer id, ContactPerson contactPerson, OwnerType ownerType, String number) {
        super(id, contactPerson, null, ownerType);
        this.number = number;
    }

    public Phone(Integer id, Employee employee, OwnerType ownerType, String number) {
        super(id, null, employee, ownerType);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

}
