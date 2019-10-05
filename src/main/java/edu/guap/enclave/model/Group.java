package edu.guap.enclave.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "groups_unique_title_idx")})
public class Group extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "object_type")
    @NotNull
    private ObjectType objectType;

    @Column(name = "comment")
    private String comment;

    public Group() {
    }

    public Group(Integer id, String title, ObjectType objectType, String comment) {
        super(id);
        this.title = title;
        this.objectType = objectType;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
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
                ", objectType=" + objectType +
                ", comment='" + comment + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
