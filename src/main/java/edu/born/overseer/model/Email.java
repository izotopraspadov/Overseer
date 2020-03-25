package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractContactEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "emails",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email", name = "email_unique_idx")
        })
@NamedQueries({
        @NamedQuery(name = Email.ALL_BY_EMPLOYEE, query = "SELECT e FROM Email e WHERE e.employee.id=:ownerId ORDER BY e.email"),
        @NamedQuery(name = Email.ALL_BY_CONTACT_PERSON, query = "SELECT e FROM Email e WHERE e.contactPerson.id=:ownerId ORDER BY e.email")
})
public class Email extends AbstractContactEntity {

    public static final String ALL_BY_EMPLOYEE = "Email.GetAllByEmployee";
    public static final String ALL_BY_CONTACT_PERSON = "Email.GetAllBContactPerson";

    @Column(name = "email", nullable = false, unique = true)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String email;

    public Email() {
    }

    public Email(Integer id, ContactPerson contactPerson, Employee employee, TypeOwner typeOwner, String email) {
        super(id, contactPerson, employee, typeOwner);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
