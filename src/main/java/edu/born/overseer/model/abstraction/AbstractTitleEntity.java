package edu.born.overseer.model.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractTitleEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "title", nullable = false, unique = true)
    protected String title;

    public AbstractTitleEntity() {
    }

    public AbstractTitleEntity(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        AbstractTitleEntity otherEntity = (AbstractTitleEntity) other;
        return title.equals(otherEntity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, title);
    }

}
