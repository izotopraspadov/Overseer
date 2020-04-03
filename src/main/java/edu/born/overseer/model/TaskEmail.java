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
        private Integer tastId;

        @Column(name = "email_id")
        private Integer emailId;

        public Id() {
        }

        public Id(Integer tastId, Integer emailId) {
            this.tastId = tastId;
            this.emailId = emailId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(tastId, id.tastId) &&
                    Objects.equals(emailId, id.emailId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tastId, emailId);
        }

    }

    @EmbeddedId
    private Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "type_send")
    @NotNull
    private SendType sendType;

    @ManyToOne
    @JoinColumn(name = "task_id",
            insertable = false, updatable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "email_id",
            insertable = false, updatable = false)
    private Email email;

    public TaskEmail() {
    }

    public TaskEmail(SendType sendType, Task task, Email email) {
        this.sendType = sendType;
        this.task = task;
        this.email = email;

        this.id.tastId = task.getId();
        this.id.emailId = email.getId();

        task.getTaskEmails().add(this);

    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
