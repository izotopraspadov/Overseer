package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salary extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "start_date", nullable = false)
    @NotNull
    // pattern
    private LocalDate dateStart;

    @Column(name = "end_date", nullable = false)
    @NotNull
    // pattern
    private LocalDate dateEnd;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    public Salary() {
    }

    public Salary(Integer id, Employee employee, LocalDate dateStart, LocalDate dateEnd, BigDecimal amount) {
        super(id);
        this.employee = employee;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", amount=" + amount +
                '}';
    }
}
