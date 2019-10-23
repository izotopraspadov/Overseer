package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordered_object_payments")
@NamedQueries({
        @NamedQuery(name = OrderedObjectPayment.ALL_BY_DATE,
                query = "SELECT oop FROM OrderedObjectPayment oop WHERE oop.date=:date ORDER BY oop.company.title"),

})
public class OrderedObjectPayment extends AbstractPaymentEntity {

    public static final String ALL_BY_DATE = "OrderedObjectPaymentRepository.getAllByDate";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordered_object_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderedObject orderedObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "our_company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company ourCompany;

    @Column(name = "comment")
    private String comment;

    public OrderedObjectPayment() {
    }

    public OrderedObjectPayment(Integer id, LocalDate date, Company company, OrderedObject orderedObject, Company ourCompany,
                                BigDecimal transaction, boolean cashless, String comment) {
        super(id, date, transaction, cashless);
        this.company = company;
        this.orderedObject = orderedObject;
        this.ourCompany = ourCompany;
        this.comment = comment;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getOurCompany() {
        return ourCompany;
    }

    public void setOurCompany(Company ourCompany) {
        this.ourCompany = ourCompany;
    }

    public OrderedObject getOrderedObject() {
        return orderedObject;
    }

    public void setOrderedObject(OrderedObject orderedObject) {
        this.orderedObject = orderedObject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderedObjectPaymentRepository{" +
                "id=" + id +
                ", date=" + date +
                ", transaction=" + transaction +
                ", cashless=" + cashless +
                ", comment='" + comment + '\'' +
                '}';
    }
}
