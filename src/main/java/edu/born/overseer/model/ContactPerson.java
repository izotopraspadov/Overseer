package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractFullNameEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "contact_persons",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id", "company_id"}, name = "contact_persons_unique_id_company_id_idx")
        }
)
@NamedQueries({
        @NamedQuery(name = ContactPerson.DELETE,
                query = "DELETE FROM ContactPerson cp WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.ALL,
                query = "SELECT cp FROM ContactPerson cp ORDER BY cp.fullName"),
        @NamedQuery(name = ContactPerson.GET,
                query = "SELECT cp FROM ContactPerson cp WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.GET_WITH_COMPANY,
                query = "SELECT cp FROM ContactPerson cp LEFT JOIN FETCH cp.company WHERE cp.id=:id"),
        @NamedQuery(name = ContactPerson.ALL_BY_COMPANY,
                query = "SELECT cp FROM ContactPerson cp WHERE cp.company.id=:companyId ORDER BY cp.fullName"),
})
public class ContactPerson extends AbstractFullNameEntity {

    public static final String DELETE = "ContactPerson.delete";
    public static final String ALL = "ContactPerson.getAll";
    public static final String GET = "ContactPerson.get";
    public static final String GET_WITH_COMPANY = "ContactPerson.getWithCompany";
    public static final String ALL_BY_COMPANY = "ContactPerson.getAllByCompany";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson")
    @OrderBy("number DESC")
    private Set<Phone> phones;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPerson")
    @OrderBy("email DESC")
    private Set<Email> emails;

    public ContactPerson() {
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
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
