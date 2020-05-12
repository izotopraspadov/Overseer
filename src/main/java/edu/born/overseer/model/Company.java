package edu.born.overseer.model;

import edu.born.overseer.annotation.ITN;
import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "companies", uniqueConstraints = {@UniqueConstraint(columnNames = "itn", name = "companies_unique_itn_idx")})
@NamedQueries({
        @NamedQuery(name = "Company:delete",
                query = "DELETE FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = "Company:byId",
                query = "SELECT c FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = "Company:byContactPerson",
                query = "SELECT c FROM Company c LEFT JOIN FETCH c.contactPersons cp WHERE cp.id=:contactPersonId"),
        @NamedQuery(name = "Company:all",
                query = "SELECT DISTINCT c FROM Company c ORDER BY c.title"),
        @NamedQuery(name = "Company:allByRegion",
                query = "SELECT c FROM Company c WHERE c.region.id=:regionId ORDER BY c.title"),
        @NamedQuery(name = "Company:allByReliability",
                query = "SELECT c FROM Company c WHERE c.reliabilityType=:reliability ORDER BY c.title"),
        @NamedQuery(name = "Company:allByType",
                query = "SELECT c FROM Company c WHERE c.companyType=:typeCompany ORDER BY c.title"),
        @NamedQuery(name = "Company:allByTitle",
                query = "SELECT c FROM Company c WHERE lower(c.title) LIKE lower(concat(:title, '%')) ORDER BY c.title"),
        @NamedQuery(name = "Company:allByAddress",
                query = "SELECT c FROM Company c WHERE lower(c.address) LIKE lower(concat(:address, '%')) ORDER BY c.title"),
        @NamedQuery(name = "Company:allByItn",
                query = "SELECT c FROM Company c WHERE lower(c.itn) LIKE lower(concat(:itn, '%')) ORDER BY c.title"),
})
public class Company extends AbstractBaseEntity {

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

    @OrderBy("fullName DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<ContactPerson> contactPersons;

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

    /**
     * Cloning constructor
     **/

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

    public List<ContactPerson> getContactPersons() {
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

    public void setContactPersons(List<ContactPerson> contactPersons) {
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

    /**
     * Fluent API
     **/

    public Company id(Integer id) {
        this.id = id;
        return this;
    }

    public Company title(String title) {
        this.title = title;
        return this;
    }

    public Company region(Region region) {
        this.region = region;
        return this;
    }

    public Company itn(String itn) {
        this.itn = itn;
        return this;
    }

    public Company address(String address) {
        this.address = address;
        return this;
    }

    public Company contactPersons(List<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
        return this;
    }

    public Company reliabilityType(ReliabilityType reliabilityType) {
        this.reliabilityType = reliabilityType;
        return this;
    }

    public Company chatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
        return this;
    }

    public Company companyType(CompanyType companyType) {
        this.companyType = companyType;
        return this;
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
