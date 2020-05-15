package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractFullNameEntity;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "employees_unique_login_idx")})
@NamedQueries({
        @NamedQuery(name = "Employee:delete",
                query = "DELETE FROM Employee e WHERE e.id=:id"),
        @NamedQuery(name = "Employee:byId",
                query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.salary s LEFT JOIN FETCH e.phones ph " +
                        "LEFT JOIN FETCH e.emails em WHERE e.id=:id ORDER BY e.fullName"),
        @NamedQuery(name = "Employee:byLogin",
                query = "SELECT e FROM Employee e WHERE e.login=:login"),
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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @NotBlank
    @Size(max = 255)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @BatchSize(size = 200)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role")
    private Set<Role> roles;

    @OrderBy("date DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<EmployeePayment> payments;

    @OrderBy("endDate DESC NULLS FIRST")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = {PERSIST, MERGE, REMOVE})
    private Set<Salary> salary;

    @OrderBy("number DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = {PERSIST, MERGE, REMOVE})
    private Set<Phone> phones;

    @OrderBy("address DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = {PERSIST, MERGE, REMOVE})
    private Set<Email> emails;

    public Employee() {
    }

    /**
     * Cloning constructor
     **/

    public Employee(Employee other) {
        super(other.getId(), other.getFullName());
        this.region = other.getRegion();
        this.address = other.getAddress();
        this.login = other.getLogin();
        this.password = other.getPassword();
        this.roles = other.getRoles();
        this.payments = other.getPayments();
        this.salary = other.getSalary();
        this.phones = other.getPhones();
        this.emails = other.getEmails();
    }

    public Region getRegion() {
        return region;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<EmployeePayment> getPayments() {
        return payments;
    }

    public Set<Salary> getSalary() {
        return salary;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPayments(Set<EmployeePayment> payments) {
        this.payments = payments;
    }

    public void setSalary(Set<Salary> salary) {
        this.salary = salary;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    /**
     * Fluent API
     **/

    public Employee id(Integer id) {
        this.id = id;
        return this;
    }

    public Employee fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Employee region(Region region) {
        this.region = region;
        return this;
    }

    public Employee login(String login) {
        this.login = login;
        return this;
    }

    public Employee password(String password) {
        this.password = password;
        return this;
    }

    public Employee address(String address) {
        this.address = address;
        return this;
    }

    public Employee roles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Employee payments(Set<EmployeePayment> payments) {
        this.payments = payments;
        return this;
    }

    public Employee salary(Set<Salary> salary) {
        this.salary = salary;
        return this;
    }

    public Employee phones(Set<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public Employee emails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Employee otherEmployee = (Employee) other;
        return Objects.equals(login, otherEmployee.login) &&
                Objects.equals(password, otherEmployee.password) &&
                Objects.equals(address, otherEmployee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, address);
    }

    @Override
    public String toString() {
        return "Employee {" +
                "id=" + id + ", " +
                "fullName='" + fullName + ", " +
                "login='" + login + ", " +
                "password='" + password + ", " +
                "address='" + address + ", " +
                "roles=" + roles +
                "}\n";
    }

}
