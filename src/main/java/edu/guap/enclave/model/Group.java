package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "groups",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title", name = "group_unique_title_idx")
        })
@NamedQueries({
        @NamedQuery(name = Group.DELETE, query = "DELETE FROM Group  g WHERE g.id=:id"),
        @NamedQuery(name = Group.ALL, query = "SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.types t ORDER BY g.title"),
        @NamedQuery(name = Group.GET, query = "SELECT g FROM Group  g WHERE g.id=:id")
})
public class Group extends AbstractBaseEntity {

    public static final String DELETE = "Group.delete";
    public static final String ALL = "Group.getAll";
    public static final String GET = "Group.get";

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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

    public Group(Integer id, String title, Set<OrderType> types, String comment) {
        super(id);
        this.title = title;
        this.types = types;
        this.comment = comment;
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
