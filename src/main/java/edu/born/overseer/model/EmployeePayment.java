package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee_payments")
@NamedQueries({
        @NamedQuery(name = "EmployeePayment:all",
                query = "SELECT ep FROM EmployeePayment ep ORDER BY ep.employee.fullName"),
        @NamedQuery(name = "EmployeePayment:allByDate",
                query = "SELECT ep FROM EmployeePayment ep WHERE ep.date=:date ORDER BY ep.employee.fullName"),
        @NamedQuery(name = "EmployeePayment:allByEmployee",
                query = "SELECT ep FROM EmployeePayment ep WHERE ep.employee.id=:employeeId ORDER BY ep.date"),
})
public class EmployeePayment extends AbstractPaymentEntity {

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

    /**
     * Cloning constructor
     **/

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

    /**
     * Fluent API
     **/

    public EmployeePayment id(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeePayment date(LocalDate date) {
        this.date = date;
        return this;
    }

    public EmployeePayment transaction(BigDecimal transaction) {
        this.transaction = transaction;
        return this;
    }

    public EmployeePayment cashless(boolean cashless) {
        this.cashless = cashless;
        return this;
    }

    public EmployeePayment employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public EmployeePayment counterpartyType(CounterpartyType counterpartyType) {
        this.counterpartyType = counterpartyType;
        return this;
    }

    public EmployeePayment companyCounterparty(Company companyCounterparty) {
        this.companyCounterparty = companyCounterparty;
        return this;
    }

    public EmployeePayment employeeCounterparty(Employee employeeCounterparty) {
        this.employeeCounterparty = employeeCounterparty;
        return this;
    }

    public EmployeePayment charge(boolean charge) {
        this.charge = charge;
        return this;
    }

    public EmployeePayment comment(String comment) {
        this.comment = comment;
        return this;
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
