package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.*;

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
@NamedQueries({
        @NamedQuery(name = Region.DELETE, query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.BY_ID, query = "SELECT r FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.BY_TITLE, query = "SELECT r FROM Region r WHERE r.title=:title"),
        @NamedQuery(name = Region.ALL, query = "SELECT r FROM Region r ORDER BY r.title"),
})
public class Region extends AbstractTitleEntity {

    public static final String DELETE = "Region:delete";
    public static final String BY_ID = "Region:byId";
    public static final String BY_TITLE = "Region:byTitle";
    public static final String ALL = "Region:all";

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
