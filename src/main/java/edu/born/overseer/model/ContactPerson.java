package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractFullNameEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "contact_persons", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "company_id"}, name = "contact_persons_unique_id_company_id_idx")})
@NamedQueries({
        @NamedQuery(name = ContactPerson.DELETE, query = "DELETE FROM ContactPerson cp WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.ALL, query = "SELECT cp FROM ContactPerson cp ORDER BY cp.fullName"),
        @NamedQuery(name = ContactPerson.BY_ID, query = "SELECT cp FROM ContactPerson cp WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.BY_ID_WITH_COMPANY, query = "SELECT cp FROM ContactPerson cp LEFT JOIN FETCH cp.company WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.ALL_BY_COMPANY, query = "SELECT cp FROM ContactPerson cp WHERE cp.company.id=:companyId ORDER BY cp.fullName"),
})
public class ContactPerson extends AbstractFullNameEntity {

    public static final String DELETE = "ContactPerson:delete";
    public static final String ALL = "ContactPerson:all";
    public static final String BY_ID = "ContactPerson:byId";
    public static final String BY_ID_WITH_COMPANY = "ContactPerson:byIdWithCompany";
    public static final String ALL_BY_COMPANY = "ContactPerson:allByCompany";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @NotNull
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson", cascade = {PERSIST, MERGE, REMOVE})
    @OrderBy("number DESC")
    private Set<Phone> phones = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson", cascade = {PERSIST, MERGE, REMOVE})
    @OrderBy("email DESC")
    private Set<Email> emails = new HashSet<>();

    public ContactPerson() {
    }

    public ContactPerson(Integer id, String fullName, Company company) {
        super(id, fullName);
        this.company = company;
    }

    public ContactPerson(Integer id, String fullName, Company company, Set<Phone> phones, Set<Email> emails) {
        super(id, fullName);
        this.company = company;
        this.phones = phones;
        this.emails = emails;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "id=" + id +
                ", fullName='" + fullName + "'" +
                "}\n";
    }

}
