package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact_person_emails",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "contact_person_emails_unique_email_idx")})
public class ContactPersonEmail extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ContactPerson contactPerson;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    public ContactPersonEmail() {
    }

    public ContactPersonEmail(Integer id, String email, ContactPerson contactPerson) {
        super(id);
        this.email = email;
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "ContactPersonEmail{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
