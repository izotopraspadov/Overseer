package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "emails")
public class Email extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ContactPerson contactPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "email", nullable = false)
    @javax.validation.constraints.Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_owner")
    @NotNull
    private TypeEmailOwner typeEmailOwner;

    public Email() {
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeEmailOwner getTypeEmailOwner() {
        return typeEmailOwner;
    }

    public void setTypeEmailOwner(TypeEmailOwner typeEmailOwner) {
        this.typeEmailOwner = typeEmailOwner;
    }

    @Override
    public String toString() {
        return "Email{" +
                "contactPerson=" + contactPerson +
                ", employee=" + employee +
                ", email='" + email + '\'' +
                ", typeEmailOwner=" + typeEmailOwner +
                ", id=" + id +
                '}';
    }
}
