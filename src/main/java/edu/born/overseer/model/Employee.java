package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractFullNameEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "employees_unique_login_idx")})
@NamedQueries({
        @NamedQuery(name = "Employee:delete",
                query = "DELETE FROM Employee e WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byId",
                query = "SELECT e FROM Employee e LEFT JOIN FETCH e.phones ph LEFT JOIN FETCH e.emails em WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byLogin",
                query = "SELECT e FROM Employee e WHERE e.login=:login"),
        @NamedQuery(name = "Employee:byIdWithPayments",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.payments WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byIdWithSalary",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.salary s WHERE e.id=:id AND s.endDate IS NULL"),
        @NamedQuery(name = "Employee:byIdWithEmails",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.emails WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byIdWithPhones",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.phones WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byIdWithSalaryAndPhonesAndEmails",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.salary s LEFT JOIN FETCH e.phones ph LEFT JOIN FETCH e.emails em WHERE e.id=:id AND s.endDate IS NULL"),
        @NamedQuery(name = "Employee:all",
                query = "SELECT e FROM Employee e ORDER BY e.fullName"),
        @NamedQuery(name = "Employee:allByRegion",
                query = "SELECT e FROM Employee e WHERE e.region.id=:regionId ORDER BY e.fullName"),
        @NamedQuery(name = "Employee:allByAddress",
                query = "SELECT e FROM Employee e WHERE lower(e.address) LIKE lower(concat(:address, '%')) ORDER BY e.fullName"),
        @NamedQuery(name = "Employee:allByFullName",
                query = "SELECT e FROM Employee e WHERE lower(e.fullName) LIKE lower(concat(:fullName, '%')) ORDER BY e.fullName"),
})
public class Employee extends AbstractFullNameEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
    @OrderBy("date DESC")
    private List<EmployeePayment> payments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "employee")
    @OrderBy("startDate DESC")
    // @Fetch(value = FetchMode.SUBSELECT)
    private Set<Salary> salary;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "employee")
    @OrderBy("number DESC")
    // @Fetch(value = FetchMode.SUBSELECT)
    private Set<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "employee")
    @OrderBy("address DESC")
    // @Fetch(value = FetchMode.SUBSELECT)
    private Set<Email> emails;

    public Employee() {
    }

    public Employee(Integer id, String fullName, Region region, String login,
                    String password, String address, Set<Role> roles, List<EmployeePayment> payments,
                    Set<Salary> salary, Set<Phone> phones, Set<Email> emails) {
        super(id, fullName);
        this.region = region;
        this.address = address;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.payments = payments;
        this.salary = salary;
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

    public List<EmployeePayment> getPayments() {
        return payments;
    }

    public void setPayments(List<EmployeePayment> payments) {
        this.payments = payments;
    }

    public Set<Salary> getSalary() {
        return salary;
    }

    public void setSalary(Set<Salary> salary) {
        this.salary = salary;
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
