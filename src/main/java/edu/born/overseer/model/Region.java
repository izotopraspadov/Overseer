package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.*;

import static edu.born.overseer.model.Region.ALL;
import static edu.born.overseer.model.Region.DELETE;

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = ALL,
                query = "SELECT r FROM Region r " +
                        "WHERE (lower(r.title) LIKE lower(concat('%', :title, '%'))) " +
                        "ORDER BY r.title")
})
public class Region extends AbstractTitleEntity {

    public static final String ALL = "Region:all";
    public static final String DELETE = "Region:delete";

    public Region() {
    }

    /**
     * Cloning constructor
     **/

    public Region(Region other) {
        super(other.getId(), other.getTitle());
    }

    /**
     * Fluent API
     **/

    public Region id(Integer id) {
        this.id = id;
        return this;
    }

    public Region title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "Region {" +
                "id=" + id + ", " +
                "title='" + title +
                "}\n";
    }

}
