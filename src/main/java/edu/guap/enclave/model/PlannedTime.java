package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "planned_time")
public class PlannedTime extends AbstractBaseEntity {

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

    @Column(name = "time", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer time;

    public PlannedTime() {
    }

    public PlannedTime(Integer id, OrderedObject object, Employee employee, Integer time) {
        super(id);
        this.object = object;
        this.employee = employee;
        this.time = time;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PlannedTime{" +
                "id=" + id +
                ", employee=" + employee +
                ", time=" + time +
                '}';
    }
}
