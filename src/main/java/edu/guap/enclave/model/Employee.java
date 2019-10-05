package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee extends AbstarctFullNameEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Region region;

    //phones

    //emails

    @Column(name = "address")
    @Size(min = 10, max = 255)
    private String address;

    public Employee() {
    }
}
