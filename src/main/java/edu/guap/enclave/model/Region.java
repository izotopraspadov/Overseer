package edu.guap.enclave.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
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
