package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import edu.guap.enclave.util.DateTimeUtil;
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
        @NamedQuery(name = Task.ALL_BY_ORDERED_OBJECT, query = "SELECT t FROM Task t WHERE t.orderedObject.id=: orderedObjectId ORDER BY t.taskDescription"),
})
public class Task extends AbstractBaseEntity {

    public static final String ALL_BY_ORDERED_OBJECT = "Task.getAllByOrderedObject";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_object_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject orderedObject;

    @Column(name = "task_description", nullable = false)
    @NotBlank
    private String taskDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "date_completed", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate dateCompleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    @NotNull
    private Result result;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<TaskEmail> taskEmails;

    public Task() {
    }

    public Task(Integer id, OrderedObject orderedObject, String taskDescription, Employee employee,
                LocalDate dateCompleted, Result result, String comment, Set<TaskEmail> taskEmails) {
        super(id);
        this.orderedObject = orderedObject;
        this.taskDescription = taskDescription;
        this.employee = employee;
        this.dateCompleted = dateCompleted;
        this.result = result;
        this.comment = comment;
        this.taskEmails = taskEmails;
    }

    public OrderedObject getOrderedObject() {
        return orderedObject;
    }

    public void setOrderedObject(OrderedObject object) {
        this.orderedObject = object;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<TaskEmail> getTaskEmails() {
        return taskEmails;
    }

    public void setTaskEmails(Set<TaskEmail> taskEmails) {
        this.taskEmails = taskEmails;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", dateCompleted=" + dateCompleted +
                ", result=" + result +
                ", comment='" + comment + '\'' +
                '}';
    }
}
