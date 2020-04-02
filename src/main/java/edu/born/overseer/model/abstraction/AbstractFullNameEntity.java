package edu.born.overseer.model.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractFullNameEntity that = (AbstractFullNameEntity) o;
        return fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullName);
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, fullName);
    }
}
