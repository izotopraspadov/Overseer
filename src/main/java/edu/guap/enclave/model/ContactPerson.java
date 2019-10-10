package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractFullNameEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = ContactPerson.DELETE,
                query = "DELETE FROM ContactPerson cp WHERE cp.id=:id AND cp.company.id=:companyId"),
        @NamedQuery(name = ContactPerson.ALL_SORTED,
                query = "SELECT cp FROM ContactPerson cp WHERE cp.company.id=:companyId ORDER BY cp.fullName"),
        @NamedQuery(name = Company.GET,
                query = "SELECT cp FROM ContactPerson cp WHERE cp.id=:id AND cp.company.id=:companyId")
})

@Entity
@Table(name = "contact_persons",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "company_id"},
                name = "contact_persons_unique_id_company_id_idx")})
public class ContactPerson extends AbstractFullNameEntity {

    public static final String DELETE = "ContactPerson.delete";
    public static final String ALL_SORTED = "ContactPerson.getAllSorted";
    public static final String GET = "ContactPerson.get";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactPerson")
    @OrderBy("number DESC")
    private List<ContactPersonPhone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactPerson")
    @OrderBy("email DESC")
    private List<ContactPersonEmail> emails;

    public ContactPerson() {
    }

    public ContactPerson(Integer id, String fullName, Company company, List<ContactPersonPhone> phones, List<ContactPersonEmail> emails) {
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

    public List<ContactPersonPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<ContactPersonPhone> phones) {
        this.phones = phones;
    }

    public List<ContactPersonEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<ContactPersonEmail> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "id=" + id +
                ", company=" + company +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
