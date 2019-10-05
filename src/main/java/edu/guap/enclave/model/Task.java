package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "task", nullable = false)
    @NotBlank
    private String task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Column(name = "date_completed", nullable = false)
    @NotNull
    // pattern
    private LocalDate dateCompleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    @NotNull
    private Result result;

    @Column(name = "comment")
    private String comment;

    //for rg

    //for manager





}
