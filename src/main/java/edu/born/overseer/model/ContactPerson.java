package edu.born.overseer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.born.overseer.model.abstraction.AbstractFullNameEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static edu.born.overseer.model.ContactPerson.BY_ID_WITH_COMPANY;
import static edu.born.overseer.model.ContactPerson.DELETE;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "contact_persons", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "company_id"}, name = "contact_persons_unique_id_company_id_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM ContactPerson cp WHERE cp.id=:id"),
        @NamedQuery(name = BY_ID_WITH_COMPANY,
                query = "SELECT cp FROM ContactPerson cp LEFT JOIN FETCH cp.company WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.BY_CONTACT_PERSON,
                query = "SELECT cp FROM ContactPerson cp WHERE cp.company.id=:companyId OR :companyId ORDER BY cp.fullName"),
})
public class ContactPerson extends AbstractFullNameEntity {

    public static final String BY_CONTACT_PERSON = "ContactPerson:all";
    public static final String DELETE = "ContactPerson:delete";
    public static final String BY_ID_WITH_COMPANY = "ContactPerson:byIdWithCompany";

    @JsonBackReference(value = "company")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @JsonManagedReference(value = "person")
    @OrderBy("number DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson", cascade = {PERSIST, MERGE, REMOVE})
    private Set<Phone> phones = new HashSet<>();

    @JsonManagedReference(value = "person")
    @OrderBy("address DESC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson", cascade = {PERSIST, MERGE, REMOVE})
    private Set<Email> emails = new HashSet<>();

    public ContactPerson() {
    }

    /**
     * Cloning constructor
     **/

    public ContactPerson(ContactPerson other) {
        super(other.getId(), other.getFullName());
        this.company = other.getCompany();
        this.phones = other.getPhones();
        this.emails = other.getEmails();
    }

    public Company getCompany() {
        return company;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    /**
     * Fluent API
     **/

    public ContactPerson id(Integer id) {
        this.id = id;
        return this;
    }

    public ContactPerson fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public ContactPerson company(Company company) {
        this.company = company;
        return this;
    }

    public ContactPerson phones(Set<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public ContactPerson emails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    // equals & hashCode only from parent class

    @Override
    public String toString() {
        return "ContactPerson {" +
                "id=" + id + ", " +
                "fullName=" + fullName +
                "}\n";
    }

}
