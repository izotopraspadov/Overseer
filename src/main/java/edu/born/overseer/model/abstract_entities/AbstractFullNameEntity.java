package edu.born.overseer.model.abstract_entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractFullNameEntity extends AbstractBaseEntity {

    @Column(name = "full_name", nullable = false)
    @NotBlank
    @Size(min = 10, max = 255)
    protected String fullName;

    public AbstractFullNameEntity() {
    }

    public AbstractFullNameEntity(Integer id, String fullName) {
        super(id);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, fullName);
    }
}