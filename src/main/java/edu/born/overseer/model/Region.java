package edu.born.overseer.model;

import edu.born.overseer.model.abstract_entities.AbstractTitleEntity;

import javax.persistence.*;

@Entity
@Table(name = "regions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")
        })
@NamedQueries({
        @NamedQuery(name = Region.DELETE, query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.ALL, query = "SELECT r FROM Region r ORDER BY r.title"),
        @NamedQuery(name = Region.GET, query = "SELECT r FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = Region.GET_BY_TITLE, query = "SELECT r FROM Region r WHERE r.title=:title")
})
public class Region extends AbstractTitleEntity {

    public static final String DELETE = "Region.delete";
    public static final String ALL = "Region.getAll";
    public static final String GET = "Region.get";
    public static final String GET_BY_TITLE = "Region.getByTitle";

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
