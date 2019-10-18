package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractContactEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "emails",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "email_unique_idx")})
public class Email extends AbstractContactEntity {

    @Column(name = "email", nullable = false, unique = true)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String email;

    public Email() {
    }

    public Email(Integer id, ContactPerson contactPerson, Employee employee, TypeEmailOwner typeOwner, String email) {
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
