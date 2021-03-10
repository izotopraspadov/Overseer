package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import edu.born.overseer.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static edu.born.overseer.model.ResultType.NOT_COMPLETED;

@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name = Task.DELETE,
                query = "DELETE FROM Task t WHERE t.id=:id"),
        @NamedQuery(name = Task.ALL_BY_ORDER,
                query = "SELECT t FROM Task t WHERE t.order.id=:orderId ORDER BY t.description")
})
public class Task extends AbstractBaseEntity {

    public static final String ALL_BY_ORDER = "Task:allByOrder";
    public static final String DELETE = "Task:delete";

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "responsible_id", nullable = false)
    private Employee responsible;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "date_completed", nullable = false)
    private LocalDate dateCompleted;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "result_type")
    private ResultType resultType = NOT_COMPLETED;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<TaskEmail> emails = new HashSet<>();

    public Task() {
    }

    /**
     * Cloning constructor
     **/

    public Task(Task other) {
        super(other.getId());
        this.order = other.getOrder();
        this.description = other.getDescription();
        this.responsible = other.getResponsible();
        this.dateCompleted = other.getDateCompleted();
        this.resultType = other.getResultType();
        this.comment = other.getComment();
        this.emails = other.getEmails();
    }

    public Order getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public String getComment() {
        return comment;
    }

    public Set<TaskEmail> getEmails() {
        return emails;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEmails(Set<TaskEmail> emails) {
        this.emails = emails;
    }

    /**
     * Fluent API
     **/

    public Task id(Integer id) {
        this.id = id;
        return this;
    }

    public Task order(Order order) {
        this.order = order;
        return this;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public Task responsible(Employee responsible) {
        this.responsible = responsible;
        return this;
    }

    public Task dateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
        return this;
    }

    public Task resultType(ResultType resultType) {
        this.resultType = resultType;
        return this;
    }

    public Task comment(String comment) {
        this.comment = comment;
        return this;
    }

    public Task emails(Set<TaskEmail> emails) {
        this.emails = emails;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Task otherTask = (Task) other;
        return Objects.equals(description, otherTask.description) &&
                Objects.equals(dateCompleted, otherTask.dateCompleted) &&
                resultType == otherTask.resultType &&
                Objects.equals(comment, otherTask.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, dateCompleted, resultType, comment);
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id + ", " +
                "description=" + description + ", " +
                "dateCompleted=" + dateCompleted + ", " +
                "result=" + resultType + ", " +
                "comment=" + comment + ", " +
                "}\n";
    }

}
