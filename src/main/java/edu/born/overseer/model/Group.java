package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "group_unique_title_idx")})
@NamedQueries({
        @NamedQuery(name = "Group:delete",
                query = "DELETE FROM Group  g WHERE g.id=:id"),
        @NamedQuery(name = "Group:byId",
                query = "SELECT g FROM Group  g WHERE g.id=:id"),
        @NamedQuery(name = "Group:all",
                query = "SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.types t ORDER BY g.title"),
})
public class Group extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @ManyToMany(cascade = {PERSIST, MERGE, REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_type_by_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "order_type_id")
    )
    private Set<OrderType> types;

    @Column(name = "comment")
    private String comment;

    public Group() {
    }

    public Group(Integer id, String title, String comment, Set<OrderType> types) {
        super(id);
        this.title = title;
        this.comment = comment;
        this.types = types;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<OrderType> getTypes() {
        return types;
    }

    public void setTypes(Set<OrderType> types) {
        this.types = types;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", types=" + types +
                ", comment='" + comment + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
