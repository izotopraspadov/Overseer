package edu.born.overseer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.born.overseer.annotation.ITN;
import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static edu.born.overseer.model.Company.ALL;
import static edu.born.overseer.model.Company.DELETE;

@Entity
@Table(name = "companies", uniqueConstraints = {@UniqueConstraint(columnNames = "itn", name = "companies_unique_itn_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = ALL,
                query = "SELECT c FROM Company c LEFT JOIN FETCH c.contactPersons cp WHERE (cp.id=:contactPersonId OR :contactPersonId IS NULL)" +
                        "AND (c.region.id=:regionId OR :regionId IS NULL) " +
                        "AND (c.reliabilityType=:reliability OR :reliability IS NULL) " +
                        "AND (c.companyType=:companyType OR :companyType IS NULL) " +
                        "AND (lower(c.title) LIKE lower(concat('%', :title, '%'))) " +
                        "AND (lower(c.address) LIKE lower(concat('%', :address, '%'))) " +
                        "AND (lower(c.itn) LIKE lower(concat('%', :itn, '%'))) ORDER BY c.title")
})
public class Company extends AbstractBaseEntity {

    public static final String ALL = "Company:all";
    public static final String DELETE = "Company:delete";

    @NotBlank
    @Size(max = 255)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ITN
    @Column(name = "itn", nullable = false, unique = true)
    private String itn;

    @NotBlank
    @Size(max = 255)
    @Column(name = "address", nullable = false)
    private String address;

    @JsonManagedReference(value = "company")
    @OrderBy("fullName DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<ContactPerson> contactPersons = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reliability_type")
    private ReliabilityType reliabilityType;

    @NotBlank
    @Size(max = 255)
    @Column(name = "chat_group_name", nullable = false)
    private String chatGroupName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    private CompanyType companyType;

    public Company() {
    }

    public Company(String title,
                   Region region, String itn,
                   String address,
                   ReliabilityType reliabilityType,
                   String chatGroupName,
                   CompanyType companyType) {
        this(null, title, region, itn, address, reliabilityType, chatGroupName, companyType);
    }

    public Company(Integer id,
                   String title,
                   Region region,
                   String itn,
                   String address,
                   ReliabilityType reliabilityType,
                   String chatGroupName,
                   CompanyType companyType) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.reliabilityType = reliabilityType;
        this.chatGroupName = chatGroupName;
        this.companyType = companyType;
    }

    public Company(Company other) {
        super(other.getId());
        this.title = other.getTitle();
        this.region = other.getRegion();
        this.itn = other.getItn();
        this.address = other.getAddress();
        this.contactPersons = other.getContactPersons();
        this.reliabilityType = other.reliabilityType;
        this.chatGroupName = other.getChatGroupName();
        this.companyType = other.getCompanyType();
    }

    public String getTitle() {
        return title;
    }

    public Region getRegion() {
        return region;
    }

    public String getItn() {
        return itn;
    }

    public String getAddress() {
        return address;
    }

    public Set<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public ReliabilityType getReliabilityType() {
        return reliabilityType;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPersons(Set<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public void setReliabilityType(ReliabilityType reliabilityType) {
        this.reliabilityType = reliabilityType;
    }

    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Company otherCompany = (Company) other;
        return title.equals(otherCompany.title) &&
                itn.equals(otherCompany.itn) &&
                address.equals(otherCompany.address) &&
                reliabilityType == otherCompany.reliabilityType &&
                chatGroupName.equals(otherCompany.chatGroupName) &&
                companyType == otherCompany.companyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, itn, address, reliabilityType, chatGroupName, companyType);
    }

    @Override
    public String toString() {
        return "Company {" +
                "id=" + id + ", " +
                "title='" + title + ", " +
                "itn='" + itn + ", " +
                "address='" + address + ", " +
                "reliability=" + reliabilityType + ", " +
                "chatGroupName='" + chatGroupName + ", " +
                "typeCompany=" + companyType + ", " +
                "}\n";
    }
}