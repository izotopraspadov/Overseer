package edu.guap.enclave.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Region.DELETE, query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.ALL_SORTED, query = "SELECT r FROM Region r ORDER BY r.title")
})

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
public class Region extends AbstractBaseEntity {

    public static final String DELETE = "Region.delete";
    public static final String ALL_SORTED = "Region.getAllSorted";

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    public Region() {
    }

    public Region(String title) {
        this.title = title;
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
