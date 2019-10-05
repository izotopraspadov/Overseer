package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends AbstractBaseEntity {

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Region region;

    @Column(name = "itn", nullable = false)
    @NotBlank
    @Size(min = 10, max = 12)
    private String itn;

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 10, max = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy("fullName DESC")
    private List<ContactPerson> contactPersons;

    @Enumerated(EnumType.STRING)
    @Column(name = "reliability")
    @NotNull
    private Reliability reliability;

    @Column(name = "whatsapp_group_name", nullable = false)
    @NotBlank
    @Size(min = 5, max = 255)
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
                ", region=" + region +
                ", itn='" + itn + '\'' +
                ", address='" + address + '\'' +
                ", reliability=" + reliability +
                ", whatsapp_group_name='" + whatsAppGroupName + '\'' +
                ", typeCompany=" + typeCompany +
                '}';
    }
}
