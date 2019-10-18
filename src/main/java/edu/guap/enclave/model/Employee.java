package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractFullNameEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "employees_unique_login_idx")})
public class Employee extends AbstractFullNameEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Region region;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 255)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;

    @Column(name = "address")
    @Size(max = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @OrderBy("number DESC")
    private List<EmployeePhone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @OrderBy("email DESC")
    private List<EmployeeEmail> emails;

    public Employee() {
    }

    public Employee(Integer id, String fullName, Region region, String address, String login,
                    String password, Set<Role> roles, List<EmployeePhone> phones, List<EmployeeEmail> emails) {
        super(id, fullName);
        this.region = region;
        this.address = address;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.phones = phones;
        this.emails = emails;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }
}
