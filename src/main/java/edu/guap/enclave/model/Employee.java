package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractFullNameEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends AbstractFullNameEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @OrderBy("number DESC")
    private List<EmployeePhone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @OrderBy("email DESC")
    private List<EmployeeEmail> emails;

    @Column(name = "address")
    @Size(min = 10, max = 255)
    private String address;

    public Employee() {
    }

    public Employee(Integer id, String fullName, Region region, List<EmployeePhone> phones, List<EmployeeEmail> emails, String address) {
        super(id, fullName);
        this.region = region;
        this.phones = phones;
        this.emails = emails;
        this.address = address;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<EmployeePhone> getPhones() {
        return phones;
    }

    public void setPhones(List<EmployeePhone> phones) {
        this.phones = phones;
    }

    public List<EmployeeEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<EmployeeEmail> emails) {
        this.emails = emails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", region=" + region +
                ", address='" + address + '\'' +
                '}';
    }
}
