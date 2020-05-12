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
import java.util.Set;

@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name = "Task:allByOrder",
                query = "SELECT t FROM Task t WHERE t.order.id=:orderId ORDER BY t.taskDescription"),
})
public class Task extends AbstractBaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank
    @Column(name = "task_description", nullable = false)
    private String taskDescription;

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
    private ResultType resultType;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<TaskEmail> taskEmails;

    public Task() {
    }

    public Order getOrder() {
        return order;
    }

    public String getTaskDescription() {
        return taskDescription;
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

    public Set<TaskEmail> getTaskEmails() {
        return taskEmails;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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

    public void setTaskEmails(Set<TaskEmail> taskEmails) {
        this.taskEmails = taskEmails;
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

    public Task taskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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

    public Task taskEmails(Set<TaskEmail> taskEmails) {
        this.taskEmails = taskEmails;
        return this;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id + ", " +
                "taskDescription=" + taskDescription + ", " +
                "dateCompleted=" + dateCompleted + ", " +
                "result=" + resultType + ", " +
                "comment=" + comment + ", " +
                "}\n";
    }

}
