package edu.guap.enclave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
public class Region extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    public Region() {
    }

    public Region(Integer id, String title) {
        super(id);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id + '\'' +
                ", title='" + title +
                '}';
    }
}
