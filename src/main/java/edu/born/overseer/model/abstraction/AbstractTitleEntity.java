package edu.born.overseer.model.abstraction;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractTitleEntity extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractTitleEntity that = (AbstractTitleEntity) o;
        return title.equals(that.title);
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
