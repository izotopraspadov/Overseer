package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import edu.guap.enclave.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "actual_time")
public class ActualTime extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_object_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject orderedObject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @Column(name = "actual_man_hours", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer actualManHours;

    @Column(name = "account_man_hours", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer accountManHours;

    public ActualTime() {
    }

    public ActualTime(Integer id, OrderedObject orderedObject, Employee employee, LocalDate date, Integer actualManHours, Integer accountManHours) {
        super(id);
        this.orderedObject = orderedObject;
        this.employee = employee;
        this.date = date;
        this.actualManHours = actualManHours;
        this.accountManHours = accountManHours;
    }

    public OrderedObject getOrderedObject() {
        return orderedObject;
    }

    public void setOrderedObject(OrderedObject orderedObject) {
        this.orderedObject = orderedObject;
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

    public Integer getActualManHours() {
        return actualManHours;
    }

    public void setActualManHours(Integer actualManHours) {
        this.actualManHours = actualManHours;
    }

    public Integer getAccountManHours() {
        return accountManHours;
    }

    public void setAccountManHours(Integer accountManHours) {
        this.accountManHours = accountManHours;
    }

    @Override
    public String toString() {
        return "ActualTime{" +
                "id=" + id +
                ", date=" + date +
                ", actualManHours=" + actualManHours +
                ", accountManHours=" + accountManHours +
                '}';
    }
}
