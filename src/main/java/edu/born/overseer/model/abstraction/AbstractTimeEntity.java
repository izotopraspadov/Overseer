package edu.born.overseer.model.abstraction;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractTimeEntity extends AbstractBaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    protected Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    protected Employee employee;

    protected AbstractTimeEntity() {
    }

    protected AbstractTimeEntity(Integer id, Order order, Employee employee) {
        super(id);
        this.order = order;
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // added fields may be lazy, override toString, equals & hashCode not needed

}
