package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractTitleEntity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Region.DELETE, query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.ALL_SORTED, query = "SELECT r FROM Region r ORDER BY r.title"),
        @NamedQuery(name = Region.GET, query = "SELECT r FROM Region r WHERE r.id=:id")
})

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
public class Region extends AbstractTitleEntity {

    public static final String DELETE = "Region.delete";
    public static final String ALL_SORTED = "Region.getAllSorted";
    public static final String GET = "Region.get";

    public Region() {
    }

    public Region(Integer id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id + '\'' +
                ", title='" + title +
                '}';
    }
}
