package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractTitleEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "order_type", uniqueConstraints = {@UniqueConstraint(columnNames = "title", name = "groups_unique_title_idx")})
public class OrderType extends AbstractTitleEntity {

    public OrderType() {
    }

    public OrderType(Integer id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
