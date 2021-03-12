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

    @NotBlank
    @Size(max = 100)
    @javax.validation.constraints.Email
    @Column(name = "address", nullable = false, unique = true)
    private String address;

    public Email() {
    }

    public Email(ContactPerson contactPerson,
                 Employee employee,
                 OwnerType ownerType,
                 String address) {
        this(null, contactPerson, employee, ownerType, address);
    }

    public Email(Integer id,
                 ContactPerson contactPerson,
                 Employee employee,
                 OwnerType ownerType,
                 String address) {
        super(id, contactPerson, employee, ownerType);
        this.address = address;
    }

    public Email(Email other) {
        super(other.getId(), other.getContactPerson(), other.getEmployee(), other.getOwnerType());
        this.address = other.getAddress();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Email email = (Email) other;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }

    @Override
    public String toString() {
        return "Email {" +
                "id=" + id + ", " +
                "address=" + address + ", " +
                "ownerType=" + ownerType +
                "}\n";
    }
}
