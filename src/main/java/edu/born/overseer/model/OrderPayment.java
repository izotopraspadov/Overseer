package edu.born.overseer.model;

import edu.born.overseer.model.abstraction.AbstractPaymentEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static edu.born.overseer.model.OrderPayment.ALL;
import static edu.born.overseer.model.OrderPayment.DELETE;

@Entity
@Table(name = "order_payments")
@NamedQueries({
        @NamedQuery(name = ALL,
                query = "SELECT op FROM OrderPayment op WHERE (op.date=:date OR :date IS NULL) " +
                        "AND (op.order.id=:orderId OR :orderId IS NULL) " +
                        "ORDER BY op.company.title"),
        @NamedQuery(name = DELETE,
                query = "DELETE FROM OrderPayment op WHERE op.id=:id")
})
public class OrderPayment extends AbstractPaymentEntity {

    public static final String ALL = "OrderPayment:all";
    public static final String DELETE = "OrderPayment:delete";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
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

    public OrderPayment(LocalDate date,
                        BigDecimal transaction,
                        boolean cashless,
                        Company company,
                        Order order,
                        Company ourCompany,
                        String comment) {
        this(null, date, transaction, cashless, company, order, ourCompany, comment);
    }

    public OrderPayment(Integer id,
                        LocalDate date,
                        BigDecimal transaction,
                        boolean cashless,
                        Company company,
                        Order order,
                        Company ourCompany,
                        String comment) {
        super(id, date, transaction, cashless);
        this.company = company;
        this.order = order;
        this.ourCompany = ourCompany;
        this.comment = comment;
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

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOurCompany(Company ourCompany) {
        this.ourCompany = ourCompany;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
