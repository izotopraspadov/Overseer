package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee_payments")
public class EmployeePayment extends AbstractPaymentEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_counterparty")
    @NotNull
    private TypeCounterparty typeCounterparty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_counterparty_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company companyCounterparty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_counterparty_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employeeCounterparty;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_payment")
    @NotNull
    private TypePayment typePayment;

    @Column(name = "comment")
    private String comment;

    public EmployeePayment() {
    }

    public EmployeePayment(Integer id, LocalDate date, BigDecimal transaction, Employee employee, TypeCounterparty typeCounterparty,
                           Company companyCounterparty, Employee employeeCounterparty, TypePayment typePayment, String comment) {
        super(id, date, transaction);
        this.employee = employee;
        this.typeCounterparty = typeCounterparty;
        this.companyCounterparty = companyCounterparty;
        this.employeeCounterparty = employeeCounterparty;
        this.typePayment = typePayment;
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

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
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
                ", employee=" + employee +
                ", typeCounterparty=" + typeCounterparty +
                ", companyCounterparty=" + companyCounterparty +
                ", employeeCounterparty=" + employeeCounterparty +
                ", typePayment=" + typePayment +
                ", comment='" + comment + '\'' +
                '}';
    }
}
