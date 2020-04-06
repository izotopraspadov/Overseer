package edu.born.overseer.transformed;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.Task;
import edu.born.overseer.model.TaskEmail;

import java.time.LocalDate;
import java.util.Set;

import static edu.born.overseer.util.ConvertTypesUtil.resultTypeToString;

public class TransformedTask extends TransformedBase {

    private Order order;

    private String taskDescription;

    private Employee responsible;

    private LocalDate dateCompleted;

    private String resultType;

    private String comment;

    private Set<TaskEmail> taskEmails;

    public TransformedTask(Task task) {
        super(task.getId());
        this.order = task.getOrder();
        this.taskDescription = task.getTaskDescription();
        this.responsible = task.getResponsible();
        this.dateCompleted = task.getDateCompleted();
        this.resultType = resultTypeToString(task.getResultType());
        this.comment = task.getComment();
        this.taskEmails = task.getTaskEmails();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
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

}
