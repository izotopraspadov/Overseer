package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "companies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "itn", name = "companies_unique_itn_idx")
        }
)
@NamedQueries({
        @NamedQuery(name = Company.DELETE,
                query = "DELETE FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = Company.ALL,
                query = "SELECT DISTINCT c FROM Company c ORDER BY c.title"),
        @NamedQuery(name = Company.GET,
                query = "SELECT c FROM Company c WHERE c.id=:id"),
        @NamedQuery(name = Company.ALL_BY_REGION,
                query = "SELECT c FROM Company c WHERE c.region.id=:regionId ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_RELIABILITY,
                query = "SELECT c FROM Company c WHERE c.reliability=:reliability ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_TYPE,
                query = "SELECT c FROM Company c WHERE c.typeCompany=:typeCompany ORDER BY c.title"),
        @NamedQuery(name = Company.ALL_BY_TITLE,
                query = "SELECT c FROM Company c WHERE c.title=:title"),
        @NamedQuery(name = Company.ALL_BY_ADDRESS,
                query = "SELECT c FROM Company c WHERE c.address=:address"),
        @NamedQuery(name = Company.ALL_BY_CONTACT_PERSON,
                query = "SELECT c FROM Company c LEFT JOIN FETCH c.contactPersons cp WHERE cp.id=:contactPersonId"),
        @NamedQuery(name = Company.FIND_BY_ITN,
                query = "SELECT c FROM Company c WHERE c.itn=:itn"),
})
public class Company extends AbstractBaseEntity {

    public static final String DELETE = "Company.delete";
    public static final String ALL = "Company.getAll";
    public static final String GET = "Company.get";
    public static final String ALL_BY_REGION = "Company.getAllByRegion";
    public static final String ALL_BY_RELIABILITY = "Company.getAllByReliability";
    public static final String ALL_BY_TYPE = "Company.getAllByType";
    public static final String ALL_BY_TITLE = "Company.getAllByTitle";
    public static final String ALL_BY_ADDRESS = "Company.getAllByAddress";
    public static final String ALL_BY_CONTACT_PERSON = "Company.findByContactPerson";
    public static final String FIND_BY_ITN = "Company.findByItn";

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

    @Column(name = "whats_app_group_name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String whatsAppGroupName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_company")
    @NotNull
    private TypeCompany typeCompany;

    public Company() {
    }

    public Company(Integer id, String title, Region region, String itn, String address, List<ContactPerson> contactPersons,
                   Reliability reliability, String whatsapp_group_name, TypeCompany typeCompany) {
        super(id);
        this.title = title;
        this.region = region;
        this.itn = itn;
        this.address = address;
        this.contactPersons = contactPersons;
        this.reliability = reliability;
        this.whatsAppGroupName = whatsapp_group_name;
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

    public String getWhatsAppGroupName() {
        return whatsAppGroupName;
    }

    public void setWhatsAppGroupName(String whatsAppGroupName) {
        this.whatsAppGroupName = whatsAppGroupName;
    }

    public TypeCompany getTypeCompany() {
        return typeCompany;
    }

    public void setTypeCompany(TypeCompany typeCompany) {
        this.typeCompany = typeCompany;
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
                ", whatsapp_group_name='" + whatsAppGroupName + '\'' +
                ", typeCompany=" + typeCompany +
                '}';
    }
}
