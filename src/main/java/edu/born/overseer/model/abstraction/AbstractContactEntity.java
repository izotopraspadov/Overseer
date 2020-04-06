package edu.born.overseer.model.abstraction;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.OwnerType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractContactEntity extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @NotNull
    private ContactPerson contactPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @NotNull
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    @NotNull
    private OwnerType ownerType;

    public AbstractContactEntity() {
    }

    public AbstractContactEntity(Integer id, ContactPerson contactPerson, Employee employee, OwnerType ownerType) {
        super(id);
        this.contactPerson = contactPerson;
        this.employee = employee;
        this.ownerType = ownerType;
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

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, ownerType);
    }
}
