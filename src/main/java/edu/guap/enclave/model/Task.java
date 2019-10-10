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

@Entity
@Table(name = "tasks")
public class Task extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject object;

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

    //for rg

    //for manager


    public Task() {
    }

    public Task(Integer id, OrderedObject object, String taskDescription, Employee employee, LocalDate dateCompleted, Result result, String comment) {
        super(id);
        this.object = object;
        this.taskDescription = taskDescription;
        this.employee = employee;
        this.dateCompleted = dateCompleted;
        this.result = result;
        this.comment = comment;
    }

    public OrderedObject getObject() {
        return object;
    }

    public void setObject(OrderedObject object) {
        this.object = object;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", employee=" + employee +
                ", dateCompleted=" + dateCompleted +
                ", result=" + result +
                ", comment='" + comment + '\'' +
                '}';
    }
}
