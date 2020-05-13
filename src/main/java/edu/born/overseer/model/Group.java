package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.*;
import java.util.Objects;
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
public class Group extends AbstractTitleEntity {

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

    /**
     * Cloning constructor
     **/

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

    /**
     * Fluent API
     **/

    public Group id(Integer id) {
        this.id = id;
        return this;
    }

    public Group title(String title) {
        this.title = title;
        return this;
    }

    public Group types(Set<OrderType> types) {
        this.types = types;
        return this;
    }

    public Group comment(String comment) {
        this.comment = comment;
        return this;
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
