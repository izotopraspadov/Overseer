package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTimeEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static edu.born.overseer.model.PlannedTime.ALL;
import static edu.born.overseer.model.PlannedTime.DELETE;

@Entity
@Table(name = "planned_time", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "order_id"}, name = "planned_time_unique_pt_object_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM PlannedTime pt WHERE pt.id=:id"),
        @NamedQuery(name = ALL,
                query = "SELECT pt FROM PlannedTime pt WHERE (pt.order.id=:orderId OR :orderId IS NULL)")
})
public class PlannedTime extends AbstractTimeEntity {

    public static final String ALL = "PlannedTime:all";
    public static final String DELETE = "PlannedTimed:delete";

    @Column(name = "man_hours", nullable = false)
    @Range(max = 5000)
    @NotNull
    private Integer manHours;

    public PlannedTime() {
    }

    public PlannedTime(Order order,
                       Employee employee,
                       Integer manHours) {
        this(null, order, employee, manHours);
    }

    public PlannedTime(Integer id,
                       Order order,
                       Employee employee,
                       Integer manHours) {
        super(id, order, employee);
        this.manHours = manHours;
    }

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
