package edu.born.overseer.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "task_emails")
@Immutable
public class TaskEmail {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "task_id")
        private Integer taskId;

        @Column(name = "email_id")
        private Integer emailId;

        public Id() {
        }

        public Id(Integer taskId, Integer emailId) {
            this.taskId = taskId;
            this.emailId = emailId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(taskId, id.taskId) &&
                    Objects.equals(emailId, id.emailId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskId, emailId);
        }

        @Override
        public String toString() {
            return "Id{" +
                    "taskId=" + taskId + ", " +
                    "emailId=" + emailId + ", " +
                    "}\n";
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "send_type")
    private SendType sendType;

    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "email_id", insertable = false, updatable = false)
    private Email email;

    public TaskEmail() {
    }

    public TaskEmail(SendType sendType, Task task, Email email) {
        this.id.taskId = task.getId();
        this.id.emailId = email.getId();
        this.sendType = sendType;
        this.task = task;
        this.email = email;
    }

    public Id getId() {
        return id;
    }

    public SendType getSendType() {
        return sendType;
    }

    public Task getTask() {
        return task;
    }

    public Email getEmail() {
        return email;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TaskEmail otherTaskEmail = (TaskEmail) other;
        return Objects.equals(id, otherTaskEmail.id) &&
                sendType == otherTaskEmail.sendType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendType);
    }

    @Override
    public String toString() {
        return "TaskEmail {" +
                "id=" + id + ", " +
                "sendType=" + sendType +
                "}\n";
    }
}
