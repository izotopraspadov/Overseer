package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.*;

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "regions_unique_title_idx")})
@NamedQueries({
        @NamedQuery(name = "Region:delete",
                query = "DELETE FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = "Region:byId",
                query = "SELECT r FROM Region r WHERE r.id=:id"),
        @NamedQuery(name = "Region:all",
                query = "SELECT r FROM Region r ORDER BY r.title"),
        @NamedQuery(name = "Region:allByTitle",
                query = "SELECT r FROM Region r WHERE lower(r.title) LIKE lower(concat(:title, '%')) ORDER BY r.title"),
})
public class Region extends AbstractTitleEntity {

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
