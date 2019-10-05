package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact_person_phones",
        uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "contact_person_phones_unique_number_idx")})
public class ContactPersonPhone extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ContactPerson contactPerson;

    @Column(name = "number", nullable = false, unique = true)
    // regex
    @NotBlank
    @Size(max = 100)
    private String number;

    public ContactPersonPhone() {
    }

    public ContactPersonPhone(Integer id, ContactPerson contactPerson, String number) {
        super(id);
        this.contactPerson = contactPerson;
        this.number = number;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ContactPersonPhone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
