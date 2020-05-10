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
        @NamedQuery(name = "Company:delete", query = "DELETE FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = "Company:byId", query = "SELECT c FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = "Company:byContactPerson", query = "SELECT c FROM Company c LEFT JOIN FETCH c.contactPersons cp WHERE cp.id=:contactPersonId"),
        @NamedQuery(name = "Company:all", query = "SELECT DISTINCT c FROM Company c ORDER BY c.title"),
        @NamedQuery(name = "Company:allByRegion", query = "SELECT c FROM Company c WHERE c.region.id=:regionId ORDER BY c.title"),
        @NamedQuery(name = "Company:allByReliability", query = "SELECT c FROM Company c WHERE c.reliabilityType=:reliability ORDER BY c.title"),
        @NamedQuery(name = "Company:allByType", query = "SELECT c FROM Company c WHERE c.companyType=:typeCompany ORDER BY c.title"),
        @NamedQuery(name = "Company:allByTitle", query = "SELECT c FROM Company c WHERE lower(c.title) LIKE lower(concat(:title, '%')) ORDER BY c.title"),
        @NamedQuery(name = "Company:allByAddress", query = "SELECT c FROM Company c WHERE lower(c.address) LIKE lower(concat(:address, '%')) ORDER BY c.title"),
        @NamedQuery(name = "Company:allByItn", query = "SELECT c FROM Company c WHERE lower(c.itn) LIKE lower(concat(:itn, '%')) ORDER BY c.title"),
})
public class Company extends AbstractBaseEntity {

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Region region;

    @Column(name = "itn", nullable = false, unique = true)
    @ITN
    private String itn;

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy("fullName DESC")
    private List<ContactPerson> contactPersons;

    @Enumerated(EnumType.STRING)
    @Column(name = "reliability_type")
    @NotNull
    private ReliabilityType reliabilityType;

    @Column(name = "chat_group_name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String chatGroupName;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type")
    @NotNull
    private CompanyType companyType;

    public Company() {
    }

    public Company(Integer id, String title, Region region, String itn, String address,
                   ReliabilityType reliabilityType, String chatGroupName, CompanyType companyType) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.reliabilityType = reliabilityType;
        this.chatGroupName = chatGroupName;
        this.companyType = companyType;
    }

    public Company(Integer id, String title, Region region, String itn, String address, List<ContactPerson> contactPersons,
                   ReliabilityType reliabilityType, String chatGroupName, CompanyType companyType) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.contactPersons = contactPersons;
        this.reliabilityType = reliabilityType;
        this.chatGroupName = chatGroupName;
        this.companyType = companyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getItn() {
        return itn;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(List<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public ReliabilityType getReliabilityType() {
        return reliabilityType;
    }

    public void setReliabilityType(ReliabilityType reliabilityType) {
        this.reliabilityType = reliabilityType;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String whatsAppGroupName) {
        this.chatGroupName = whatsAppGroupName;
    }

    public CompanyType getCompanyType() {
        return companyType;
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
        return "Company{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", itn='" + itn + '\'' +
                ", address='" + address + '\'' +
                ", reliability=" + reliabilityType +
                ", region='" + region.getTitle() + '\'' +
                ", chatGroupName='" + chatGroupName + '\'' +
                ", typeCompany=" + companyType +
                '}';
    }
}
