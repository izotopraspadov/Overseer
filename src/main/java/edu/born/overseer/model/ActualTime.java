package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import edu.born.overseer.util.ArgumentHasNoDefaultInitializationException;
import edu.born.overseer.util.BuilderUtil;
import edu.born.overseer.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "actual_time", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "order_id"}, name = "actual_time_unique_at_object_idx")})
@NamedQueries({
        @NamedQuery(name = "ActualTime:delete",
                query = "DELETE FROM ActualTime at WHERE at.id=:id"),
        @NamedQuery(name = "ActualTime:allByOrder",
                query = "SELECT at FROM ActualTime at WHERE at.order.id=:orderId")
})
public class ActualTime extends AbstractBaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Range(max = 5000)
    @Column(name = "actual_man_hours", nullable = false)
    private Integer actualManHours;

    @NotNull
    @Range(max = 5000)
    @Column(name = "account_man_hours", nullable = false)
    private Integer accountManHours;

    protected ActualTime() {
    }

    public Order getOrder() {
        return order;
    }

    public Employee getEmployee() {
        return employee;
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

    public static Builder newBuilder() {
        return new ActualTime().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Integer id) {
            ActualTime.this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            ActualTime.this.order = order;
            return this;
        }

        public Builder setEmployee(Employee employee) {
            ActualTime.this.employee = employee;
            return this;
        }

        public Builder setDate(LocalDate date) {
            ActualTime.this.date = date;
            return this;
        }

        public Builder setActualManHours(Integer actualManHours) {
            ActualTime.this.actualManHours = actualManHours;
            return this;
        }

        public Builder setAccountManHours(Integer accountManHours) {
            ActualTime.this.accountManHours = accountManHours;
            return this;
        }

        public ActualTime build() {
            if (BuilderUtil.isNull(order, employee, date, actualManHours, accountManHours))
                throw new ArgumentHasNoDefaultInitializationException();
            else return ActualTime.this;
        }

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
