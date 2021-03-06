package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTimeEntity;
import edu.born.overseer.util.DateTimeUtil;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static edu.born.overseer.model.ActualTime.ALL_BY_ORDER;
import static edu.born.overseer.model.ActualTime.DELETE;

@Entity
@Table(name = "actual_time", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "order_id"}, name = "actual_time_unique_at_object_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM ActualTime at WHERE at.id=:id"),
        @NamedQuery(name = ALL_BY_ORDER,
                query = "SELECT at FROM ActualTime at WHERE at.order.id=:orderId")
})
public class ActualTime extends AbstractTimeEntity {

    public static final String ALL_BY_ORDER = "ActualTime:allByOrder";
    public static final String DELETE = "ActualTime:delete";

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Range(max = 5000)
    @Column(name = "actual_man_hours", nullable = false)
    private Integer actualManHours = 0;

    @NotNull
    @Range(max = 5000)
    @Column(name = "account_man_hours", nullable = false)
    private Integer accountManHours;

    public ActualTime() {
    }

    public ActualTime(Order order,
                      Employee employee,
                      LocalDate date,
                      Integer actualManHours,
                      Integer accountManHours) {
        this(null, order, employee, date, actualManHours, accountManHours);
    }

    public ActualTime(Integer id,
                      Order order,
                      Employee employee,
                      LocalDate date,
                      Integer actualManHours,
                      Integer accountManHours) {
        super(id, order, employee);
        this.date = date;
        this.actualManHours = actualManHours;
        this.accountManHours = accountManHours;
    }

    public ActualTime(ActualTime other) {
        super(other.getId(), other.getOrder(), other.getEmployee());
        this.date = other.getDate();
        this.actualManHours = other.getActualManHours();
        this.accountManHours = other.getAccountManHours();
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getActualManHours() {
        return actualManHours;
    }

    public Integer getAccountManHours() {
        return accountManHours;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setActualManHours(Integer actualManHours) {
        this.actualManHours = actualManHours;
    }

    public void setAccountManHours(Integer accountManHours) {
        this.accountManHours = accountManHours;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        ActualTime otherActualTime = (ActualTime) other;
        return Objects.equals(date, otherActualTime.date) &&
                Objects.equals(actualManHours, otherActualTime.actualManHours) &&
                Objects.equals(accountManHours, otherActualTime.accountManHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, actualManHours, accountManHours);
    }

    @Override
    public String toString() {
        return "ActualTime {" +
                "id=" + id + ", " +
                "date=" + date + ", " +
                "actualManHours=" + actualManHours + ", " +
                "accountManHours=" + accountManHours + ", " +
                "}\n";
    }
}
