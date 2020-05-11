package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractContactEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "emails", uniqueConstraints = {@UniqueConstraint(columnNames = "address", name = "email_unique_idx")})
@NamedQueries({
        @NamedQuery(name = "Email:allByEmployee",
                query = "SELECT e FROM Email e WHERE e.employee.id=:ownerId ORDER BY e.address"),
        @NamedQuery(name = "Email:allBContactPerson",
                query = "SELECT e FROM Email e WHERE e.contactPerson.id=:ownerId ORDER BY e.address")
})
public class Email extends AbstractContactEntity {

    @Column(name = "address", nullable = false, unique = true)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String address;

    public Email() {
    }

    public Email(Integer id, ContactPerson contactPerson, OwnerType ownerType, String address) {
        super(id, contactPerson, null, ownerType);
        this.address = address;
    }

    public Email(Integer id, Employee employee, OwnerType ownerType, String address) {
        super(id, null, employee, ownerType);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String email) {
        this.address = email;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Email otherEmail = (Email) other;
        return Objects.equals(address, otherEmail.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

}
