package edu.born.overseer.model;

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
        @NamedQuery(name = Company.DELETE, query = "DELETE FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = Company.ALL, query = "SELECT DISTINCT c FROM Company c ORDER BY c.title"),
        @NamedQuery(name = Company.BY_ID, query = "SELECT c FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = Company.ALL_BY_REGION, query = "SELECT c FROM Company c WHERE c.region.id=:regionId ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_RELIABILITY, query = "SELECT c FROM Company c WHERE c.reliability=:reliability ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_TYPE, query = "SELECT c FROM Company c WHERE c.typeCompany=:typeCompany ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_TITLE, query = "SELECT c FROM Company c WHERE lower(c.title) LIKE lower(concat(:title, '%')) ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_ADDRESS, query = "SELECT c FROM Company c WHERE lower(c.address) LIKE lower(concat(:address, '%')) ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_CONTACT_PERSON, query = "SELECT c FROM Company c LEFT JOIN FETCH c.contactPersons cp WHERE cp.id=:contactPersonId"),
        @NamedQuery(name = Company.ALL_BY_ITN, query = "SELECT c FROM Company c WHERE lower(c.itn) LIKE lower(concat(:itn, '%')) ORDER BY c.title"),
})
public class Company extends AbstractBaseEntity {

    public static final String DELETE = "Company:delete";
    public static final String ALL = "Company:all";
    public static final String BY_ID = "Company:byId";
    public static final String ALL_BY_REGION = "Company:allByRegion";
    public static final String ALL_BY_RELIABILITY = "Company:allByReliability";
    public static final String ALL_BY_TYPE = "Company:allByType";
    public static final String ALL_BY_TITLE = "Company:allByTitle";
    public static final String ALL_BY_ADDRESS = "Company:allByAddress";
    public static final String ALL_BY_CONTACT_PERSON = "Company:allByContactPerson";
    public static final String ALL_BY_ITN = "Company:allByItn";

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
    @NotBlank
    @Size(min = 10, max = 12)
    private String itn;

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy("fullName DESC")
    private List<ContactPerson> contactPersons;

    @Enumerated(EnumType.STRING)
    @Column(name = "reliability")
    @NotNull
    private Reliability reliability;

    @Column(name = "chat_group_name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String chatGroupName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_company")
    @NotNull
    private TypeCompany typeCompany;

    public Company() {
    }

    public Company(Integer id, String title, Region region, String itn, String address,
                   Reliability reliability, String chatGroupName, TypeCompany typeCompany) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.reliability = reliability;
        this.chatGroupName = chatGroupName;
        this.typeCompany = typeCompany;
    }

    public Company(Integer id, String title, Region region, String itn, String address, List<ContactPerson> contactPersons,
                   Reliability reliability, String chatGroupName, TypeCompany typeCompany) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.contactPersons = contactPersons;
        this.reliability = reliability;
        this.chatGroupName = chatGroupName;
        this.typeCompany = typeCompany;
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

    public Reliability getReliability() {
        return reliability;
    }

    public void setReliability(Reliability reliability) {
        this.reliability = reliability;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String whatsAppGroupName) {
        this.chatGroupName = whatsAppGroupName;
    }

    public TypeCompany getTypeCompany() {
        return typeCompany;
    }

    public void setTypeCompany(TypeCompany typeCompany) {
        this.typeCompany = typeCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return title.equals(company.title) &&
                itn.equals(company.itn) &&
                address.equals(company.address) &&
                reliability == company.reliability &&
                chatGroupName.equals(company.chatGroupName) &&
                typeCompany == company.typeCompany;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, itn, address, reliability, chatGroupName, typeCompany);
    }

    @Override
    public String toString() {
        return "Company{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", itn='" + itn + '\'' +
                ", address='" + address + '\'' +
                ", reliability=" + reliability +
                ", region='" + region.getTitle() + '\'' +
                ", chatGroupName='" + chatGroupName + '\'' +
                ", typeCompany=" + typeCompany +
                '}';
    }
}
