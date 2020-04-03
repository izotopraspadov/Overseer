package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_payments")
@NamedQueries({
        @NamedQuery(name = OrderPayment.ALL,
                query = "SELECT oop FROM OrderPayment oop ORDER BY oop.company.title"),
        @NamedQuery(name = OrderPayment.ALL_BY_DATE,
                query = "SELECT oop FROM OrderPayment oop WHERE oop.date=:date ORDER BY oop.company.title"),
        @NamedQuery(name = OrderPayment.ALL_BY_ORDER,
                query = "SELECT oop FROM OrderPayment oop WHERE oop.order.id=:orderId ORDER BY oop.company.title"),
})
public class OrderPayment extends AbstractPaymentEntity {

    public static final String ALL = "OrderedObjectPaymentRepository.getAll";
    public static final String ALL_BY_DATE = "OrderedObjectPaymentRepository.getAllByDate";
    public static final String ALL_BY_ORDER = "OrderedObjectPaymentRepository.getAllByOrder";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "our_company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company ourCompany;

    @Column(name = "comment")
    private String comment;

    public OrderPayment() {
    }

    public OrderPayment(Integer id, LocalDate date, Company company, Order order, Company ourCompany,
                        BigDecimal transaction, boolean cashless, String comment) {
        super(id, date, transaction, cashless);
        this.company = company;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
                ", company=" + company.getTitle() +
                ", date=" + date +
                ", transaction=" + transaction +
                ", cashless=" + cashless +
                ", comment='" + comment + '\'' +
                '}';
    }
}
