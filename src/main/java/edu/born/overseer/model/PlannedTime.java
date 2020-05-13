package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTimeEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "planned_time", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "order_id"}, name = "planned_time_unique_pt_object_idx")})
@NamedQueries({
        @NamedQuery(name = "PlannedTimed:delete",
                query = "DELETE FROM PlannedTime pt WHERE pt.id=:id"),
        @NamedQuery(name = "PlannedTime:allByOrder",
                query = "SELECT pt FROM PlannedTime pt WHERE pt.order.id=:orderId")
})
public class PlannedTime extends AbstractTimeEntity {

    @Column(name = "man_hours", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer manHours;

    public PlannedTime() {
    }

    /**
     * Cloning constructor
     **/

    public PlannedTime(PlannedTime other) {
        super(other.getId(), other.getOrder(), other.getEmployee());
        this.manHours = other.getManHours();
    }

    public Integer getManHours() {
        return manHours;
    }

    public void setManHours(Integer manHours) {
        this.manHours = manHours;
    }

    /**
     * Fluent API
     **/

    public PlannedTime id(Integer id) {
        this.id = id;
        return this;
    }

    public PlannedTime order(Order order) {
        this.order = order;
        return this;
    }

    public PlannedTime employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public PlannedTime manHours(Integer manHours) {
        this.manHours = manHours;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        PlannedTime that = (PlannedTime) other;
        return Objects.equals(manHours, that.manHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manHours);
    }

    @Override
    public String toString() {
        return "PlannedTime {" +
                "id=" + id + ", " +
                "manHours=" + manHours +
                "}\n";
    }

}
