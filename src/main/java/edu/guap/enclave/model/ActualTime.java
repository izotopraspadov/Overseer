package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "actual_time")
public class ActualTime extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject object;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "date", nullable = false)
    @NotNull
    // pattern
    private LocalDate date;

    @Column(name = "actual_time", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer actualTime;

    @Column(name = "accounting_time", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer accountingTime;

    public ActualTime() {
    }

    public ActualTime(Integer id, OrderedObject object, Employee employee, LocalDate date, Integer actualTime, Integer accountingTime) {
        super(id);
        this.object = object;
        this.employee = employee;
        this.date = date;
        this.actualTime = actualTime;
        this.accountingTime = accountingTime;
    }

    public OrderedObject getObject() {
        return object;
    }

    public void setObject(OrderedObject object) {
        this.object = object;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getActualTime() {
        return actualTime;
    }

    public void setActualTime(Integer actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getAccountingTime() {
        return accountingTime;
    }

    public void setAccountingTime(Integer accountingTime) {
        this.accountingTime = accountingTime;
    }

    @Override
    public String toString() {
        return "ActualTime{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                ", actualTime=" + actualTime +
                ", accountingTime=" + accountingTime +
                '}';
    }
}
