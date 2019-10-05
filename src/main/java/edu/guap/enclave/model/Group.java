package edu.guap.enclave.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "groups")
public class Group extends AbstractTitleEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "object_type")
    @NotNull
    private ObjectType objectType;

    @Column(name = "comment")
    private String comment;

    public Group() {
    }

    public Group(Integer id, String title, ObjectType objectType, String comment) {
        super(id, title);
        this.objectType = objectType;
        this.comment = comment;
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
