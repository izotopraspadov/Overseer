package edu.born.overseer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static edu.born.overseer.model.EmployeePayment.ALL;
import static edu.born.overseer.model.EmployeePayment.DELETE;

@Entity
@Table(name = "employee_payments")
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM EmployeePayment ep WHERE ep.id=:id"),
        @NamedQuery(name = ALL,
                query = "SELECT ep FROM EmployeePayment ep WHERE (ep.date=:date OR :date IS NULL)" +
                        "AND (ep.employee.id=:employeeId OR :employeeId IS NULL) " +
                        "ORDER BY ep.employee.fullName")
})
public class EmployeePayment extends AbstractPaymentEntity {

    public static final String ALL = "EmployeePayment:all";
    public static final String DELETE = "EmployeePayment:delete";

    @JsonBackReference(value = "employee")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "counterparty_type")
    private CounterpartyType counterpartyType;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_counterparty_id")
    private Company companyCounterparty;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_counterparty_id")
    private Employee employeeCounterparty;

    @Column(name = "charge", nullable = false)
    private boolean charge = false;

    @Column(name = "comment")
    private String comment;

    public EmployeePayment() {
    }

    public EmployeePayment(LocalDate date,
                           BigDecimal transaction,
                           boolean cashless,
                           Employee employee,
                           CounterpartyType counterpartyType,
                           Company companyCounterparty,
                           Employee employeeCounterparty,
                           boolean charge,
                           String comment) {
        this(null, date, transaction, cashless, employee, counterpartyType,
                companyCounterparty, employeeCounterparty, charge, comment);
    }

    public EmployeePayment(Integer id,
                           LocalDate date,
                           BigDecimal transaction,
                           boolean cashless,
                           Employee employee,
                           CounterpartyType counterpartyType,
                           Company companyCounterparty,
                           Employee employeeCounterparty,
                           boolean charge,
                           String comment) {
        super(id, date, transaction, cashless);
        this.employee = employee;
        this.counterpartyType = counterpartyType;
        this.companyCounterparty = companyCounterparty;
        this.employeeCounterparty = employeeCounterparty;
        this.charge = charge;
        this.comment = comment;
    }

    public EmployeePayment(EmployeePayment other) {
        super(other.getId(), other.getDate(), other.getTransaction(), other.isCashless());
        this.employee = other.getEmployee();
        this.counterpartyType = other.getCounterpartyType();
        this.companyCounterparty = other.getCompanyCounterparty();
        this.employeeCounterparty = other.getEmployeeCounterparty();
        this.charge = other.isCharge();
        this.comment = other.getComment();
    }

    public Employee getEmployee() {
        return employee;
    }

    public CounterpartyType getCounterpartyType() {
        return counterpartyType;
    }

    public Company getCompanyCounterparty() {
        return companyCounterparty;
    }

    public Employee getEmployeeCounterparty() {
        return employeeCounterparty;
    }

    public boolean isCharge() {
        return charge;
    }

    public String getComment() {
        return comment;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCounterpartyType(CounterpartyType counterpartyType) {
        this.counterpartyType = counterpartyType;
    }

    public void setCompanyCounterparty(Company companyCounterparty) {
        this.companyCounterparty = companyCounterparty;
    }

    public void setEmployeeCounterparty(Employee employeeCounterparty) {
        this.employeeCounterparty = employeeCounterparty;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        EmployeePayment otherEmployeePayment = (EmployeePayment) other;
        return charge == otherEmployeePayment.charge &&
                counterpartyType == otherEmployeePayment.counterpartyType &&
                Objects.equals(comment, otherEmployeePayment.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), counterpartyType, charge, comment);
    }

    @Override
    public String toString() {
        return "EmployeePayment{" +
                "id=" + id + ", " +
                "date=" + date + ", " +
                "transaction=" + transaction + ", " +
                "cashless=" + cashless + ", " +
                "typeCounterparty=" + counterpartyType + ", " +
                "charge=" + charge + ", " +
                "comment=" + comment +
                '}';
    }
}
