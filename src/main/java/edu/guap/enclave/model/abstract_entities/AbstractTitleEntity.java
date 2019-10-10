package edu.guap.enclave.model.abstract_entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, title);
    }
}
