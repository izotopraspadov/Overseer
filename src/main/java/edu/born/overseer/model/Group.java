package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static edu.born.overseer.model.Group.DELETE;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "group_unique_title_idx")})
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM Group  g WHERE g.id=:id"),
        @NamedQuery(name = Group.ALL,
                query = "SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.types t ORDER BY g.title"),
})
public class Group extends AbstractTitleEntity {

    public static final String ALL = "Group:all";
    public static final String DELETE = "Group:delete";

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

    public Group(String title, Set<OrderType> types, String comment) {
        this(null, title, types, comment);
    }

    public Group(Integer id, String title, Set<OrderType> types, String comment) {
        super(id, title);
        this.types = types;
        this.comment = comment;
    }

    public Group(Group other) {
        super(other.getId(), other.getTitle());
        this.comment = other.getComment();
        this.types = other.getTypes();
    }

    public Set<OrderType> getTypes() {
        return types;
    }

    public String getComment() {
        return comment;
    }

    public void setTypes(Set<OrderType> types) {
        this.types = types;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Group otherGroup = (Group) other;
        return Objects.equals(comment, otherGroup.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), comment);
    }

    @Override
    public String toString() {
        return "Group {" +
                "id=" + id + ", " +
                "title='" + title +
                "comment='" + comment + ", " +
                "}\n";
    }
}
