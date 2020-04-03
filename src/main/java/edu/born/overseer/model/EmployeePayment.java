package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee_payments")
@NamedQueries({
        @NamedQuery(name = EmployeePayment.ALL, query = "SELECT ep FROM EmployeePayment ep ORDER BY ep.employee.fullName"),
        @NamedQuery(name = EmployeePayment.ALL_BY_DATE, query = "SELECT ep FROM EmployeePayment ep WHERE ep.date=:date ORDER BY ep.employee.fullName"),
        @NamedQuery(name = EmployeePayment.ALL_BY_EMPLOYEE, query = "SELECT ep FROM EmployeePayment ep WHERE ep.employee.id=:employeeId ORDER BY ep.date"),
})
public class EmployeePayment extends AbstractPaymentEntity {

    public static final String ALL = "EmployeePayment:all";
    public static final String ALL_BY_DATE = "EmployeePayment:allByDate";
    public static final String ALL_BY_EMPLOYEE = "EmployeePayment:allByEmployee";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_counterparty")
    @NotNull
    private TypeCounterparty typeCounterparty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_counterparty_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company companyCounterparty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_counterparty_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employeeCounterparty;

    @Column(name = "charge", nullable = false)
    private boolean charge;

    @Column(name = "comment")
    private String comment;

    public EmployeePayment() {
    }

    public EmployeePayment(Integer id, LocalDate date, BigDecimal transaction, Employee employee, TypeCounterparty typeCounterparty,
                           Company companyCounterparty, Employee employeeCounterparty,
                           boolean cashless, boolean charge, String comment) {
        super(id, date, transaction, cashless);
        this.employee = employee;
        this.typeCounterparty = typeCounterparty;
        this.companyCounterparty = companyCounterparty;
        this.employeeCounterparty = employeeCounterparty;
        this.charge = charge;
        this.comment = comment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TypeCounterparty getTypeCounterparty() {
        return typeCounterparty;
    }

    public void setTypeCounterparty(TypeCounterparty typeCounterparty) {
        this.typeCounterparty = typeCounterparty;
    }

    public Company getCompanyCounterparty() {
        return companyCounterparty;
    }

    public void setCompanyCounterparty(Company companyCounterparty) {
        this.companyCounterparty = companyCounterparty;
    }

    public Employee getEmployeeCounterparty() {
        return employeeCounterparty;
    }

    public void setEmployeeCounterparty(Employee employeeCounterparty) {
        this.employeeCounterparty = employeeCounterparty;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "EmployeePayment{" +
                "id=" + id +
                ", employee=" + employee.getFullName() +
                ", transaction=" + transaction +
                ", typeCounterparty=" + typeCounterparty +
                ", cashless=" + cashless +
                ", charge=" + charge +
                ", comment='" + comment + '\'' +
                '}';
    }
}
