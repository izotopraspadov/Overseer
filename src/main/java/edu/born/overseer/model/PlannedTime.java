package edu.born.overseer.model;

import edu.born.overseer.model.abstract_entities.AbstractBaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "planned_time",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id", "ordered_object_id"}, name = "planned_time_unique_pt_object_idx")
        })

@NamedQueries({
        @NamedQuery(name = PlannedTime.DELETE,
                query = "DELETE FROM PlannedTime pt WHERE pt.id=:id"),
        @NamedQuery(name = PlannedTime.ALL_BY_ORDERED_OBJECT,
                query = "SELECT pt FROM PlannedTime pt WHERE pt.orderedObject.id=:orderedObjectId")

})
public class PlannedTime extends AbstractBaseEntity {

    public static final String DELETE = "PlannedTimed.delete";
    public static final String ALL_BY_ORDERED_OBJECT = "PlannedTime.getAllByOrderedObject";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_object_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject orderedObject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "man_hours", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer manHours;

    public PlannedTime() {
    }

    public PlannedTime(Integer id, OrderedObject orderedObject, Employee employee, Integer manHours) {
        super(id);
        this.orderedObject = orderedObject;
        this.employee = employee;
        this.manHours = manHours;
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

    public Integer getManHours() {
        return manHours;
    }

    public void setManHours(Integer manHours) {
        this.manHours = manHours;
    }

    @Override
    public String toString() {
        return "PlannedTime{" +
                "id=" + id +
                ", manHours=" + manHours +
                '}';
    }
}
