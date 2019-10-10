package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee_phones",
        uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "employee_phones_unique_number_idx")})
public class EmployeePhone extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "number", nullable = false, unique = true)
    // regex
    @NotBlank
    @Size(max = 100)
    private String number;

    public EmployeePhone() {
    }

    public EmployeePhone(Integer id, Employee employee, String number) {
        super(id);
        this.employee = employee;
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "EmployeePhone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
