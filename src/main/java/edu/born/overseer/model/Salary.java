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
import java.util.Objects;

@Entity
@Table(name = "salaries", uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id", "start_date"}, name = "salaries_unique_employee_start_date_idx")})
@NamedQueries({
        @NamedQuery(name = "Salary:delete",
                query = "DELETE FROM Salary s WHERE s.id=:id"),
        @NamedQuery(name = "Salary:currentByEmployee",
                query = "SELECT s FROM Salary s WHERE s.employee.id=:employeeId AND s.endDate IS NULL"),
        @NamedQuery(name = "Salary:allByEmployee",
                query = "SELECT s FROM Salary s WHERE s.employee.id=:employeeId ORDER BY endDate DESC NULLS FIRST"),
})
public class Salary extends AbstractBaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "start_date", nullable = false, unique = true)
    private LocalDate startDate;

    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Column(name = "amount")
    private BigDecimal amount;

    public Salary() {
    }

    /**
     * Cloning constructor
     **/

    public Salary(Salary other) {
        super(other.getId());
        this.employee = other.getEmployee();
        this.startDate = other.getStartDate();
        this.endDate = other.getEndDate();
        this.amount = other.getAmount();
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Fluent API
     **/

    public Salary id(Integer id) {
        this.id = id;
        return this;
    }

    public Salary employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Salary startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public Salary endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public Salary amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Salary otherSalary = (Salary) other;
        return Objects.equals(startDate, otherSalary.startDate) &&
                Objects.equals(endDate, otherSalary.endDate) &&
                Objects.equals(amount, otherSalary.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDate, endDate, amount);
    }

    @Override
    public String toString() {
        return "Salary {" +
                "id=" + id + ", " +
                "dateStart=" + startDate + ", " +
                "dateEnd=" + endDate + ", " +
                "amount=" + amount +
                "}\n";
    }

}
