package edu.guap.enclave.model.abstract_entities;

import edu.guap.enclave.model.ContactPerson;
import edu.guap.enclave.model.Employee;
import edu.guap.enclave.model.TypeOwner;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractContactEntity extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ContactPerson contactPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_owner")
    @NotNull
    private TypeOwner typeOwner;

    public AbstractContactEntity() {
    }

    public AbstractContactEntity(Integer id, ContactPerson contactPerson, Employee employee, TypeOwner typeOwner) {
        super(id);
        this.contactPerson = contactPerson;
        this.employee = employee;
        this.typeOwner = typeOwner;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TypeOwner getTypeOwner() {
        return typeOwner;
    }

    public void setTypeOwner(TypeOwner typeOwner) {
        this.typeOwner = typeOwner;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, typeOwner);
    }
}
