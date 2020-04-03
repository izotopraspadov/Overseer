package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractContactEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "emails", uniqueConstraints = {@UniqueConstraint(columnNames = "address", name = "email_unique_idx")})
@NamedQueries({
        @NamedQuery(name = Email.ALL_BY_EMPLOYEE, query = "SELECT e FROM Email e WHERE e.employee.id=:ownerId ORDER BY e.address"),
        @NamedQuery(name = Email.ALL_BY_CONTACT_PERSON, query = "SELECT e FROM Email e WHERE e.contactPerson.id=:ownerId ORDER BY e.address")
})
public class Email extends AbstractContactEntity {

    public static final String ALL_BY_EMPLOYEE = "Email:allByEmployee";
    public static final String ALL_BY_CONTACT_PERSON = "Email:allBContactPerson";

    @Column(name = "address", nullable = false, unique = true)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String address;

    public Email() {
    }

    public Email(Integer id, ContactPerson contactPerson, TypeOwner typeOwner, String address) {
        super(id, contactPerson, null, typeOwner);
        this.address = address;
    }

    public Email(Integer id, Employee employee, TypeOwner typeOwner, String address) {
        super(id, null, employee, typeOwner);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String email) {
        this.address = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Email email1 = (Email) o;
        return Objects.equals(address, email1.address);
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
