package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import edu.born.overseer.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salaries",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id", "start_date"},
                name = "salaries_unique_employee_start_date_idx")})
public class Salary extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "start_date", nullable = false, unique = true)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate endDate;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    public Salary() {
    }

    public Salary(Integer id, Employee employee, LocalDate startDate, LocalDate endDate, BigDecimal amount) {
        super(id);
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate dateEnd) {
        this.endDate = dateEnd;
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
                ", dateStart=" + startDate +
                ", dateEnd=" + endDate +
                ", amount=" + amount +
                '}';
    }
}
