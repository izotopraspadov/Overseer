package edu.born.overseer.model.abstraction;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.OwnerType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractContactEntity extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contact_person_id")
    protected ContactPerson contactPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id")
    protected Employee employee;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    protected OwnerType ownerType;

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
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        AbstractContactEntity otherAbstractContactEntity = (AbstractContactEntity) other;
        return ownerType == otherAbstractContactEntity.ownerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ownerType);
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, ownerType);
    }

}
