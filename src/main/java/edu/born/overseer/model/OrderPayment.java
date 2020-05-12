package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "order_payments")
@NamedQueries({
        @NamedQuery(name = "OrderPayment:all",
                query = "SELECT op FROM OrderPayment op ORDER BY op.company.title"),
        @NamedQuery(name = "OrderPayment:allByDate",
                query = "SELECT op FROM OrderPayment op WHERE op.date=:date ORDER BY op.company.title"),
        @NamedQuery(name = "OrderPayment:allByOrder",
                query = "SELECT op FROM OrderPayment op WHERE op.order.id=:orderId ORDER BY op.company.title"),
})
public class OrderPayment extends AbstractPaymentEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "our_company_id", nullable = false)
    private Company ourCompany;

    @Column(name = "comment")
    private String comment;

    public OrderPayment() {
    }

    public Company getCompany() {
        return company;
    }

    public Order getOrder() {
        return order;
    }

    public Company getOurCompany() {
        return ourCompany;
    }

    public String getComment() {
        return comment;
    }

    public OrderPayment Company(Company company) {
        this.company = company;
        return this;
    }

    public OrderPayment Order(Order order) {
        this.order = order;
        return this;
    }

    public OrderPayment OurCompany(Company ourCompany) {
        this.ourCompany = ourCompany;
        return this;
    }

    public OrderPayment Comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Fluent API
     **/

    public OrderPayment date(LocalDate date) {
        this.date = date;
        return this;
    }

    public OrderPayment transaction(BigDecimal transaction) {
        this.transaction = transaction;
        return this;
    }

    public OrderPayment cashless(boolean cashless) {
        this.cashless = cashless;
        return this;
    }

    public OrderPayment company(Company company) {
        this.company = company;
        return this;
    }

    public OrderPayment order(Order order) {
        this.order = order;
        return this;
    }

    public OrderPayment ourCompany(Company ourCompany) {
        this.ourCompany = ourCompany;
        return this;
    }

    public OrderPayment comment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        OrderPayment otherPayment = (OrderPayment) other;
        return Objects.equals(company, otherPayment.company) &&
                Objects.equals(order, otherPayment.order) &&
                Objects.equals(ourCompany, otherPayment.ourCompany) &&
                Objects.equals(comment, otherPayment.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, order, ourCompany, comment);
    }

    @Override
    public String toString() {
        return "OrderPayment {" +
                "id=" + id + ", " +
                "date=" + date + ", " +
                "transaction=" + transaction + ", " +
                "cashless=" + cashless + ", " +
                "comment='" + comment + ", " +
                "}\n";
    }

}
