package edu.born.overseer.model.abstraction;

import edu.born.overseer.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.RoundingMode.DOWN;

@MappedSuperclass
public abstract class AbstractPaymentEntity extends AbstractBaseEntity {

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    protected LocalDate date = LocalDate.now();

    @NotNull
    @Digits(integer = 11, fraction = 2)
    @Column(name = "transaction", nullable = false)
    protected BigDecimal transaction;

    @Column(name = "cashless", nullable = false)
    protected boolean cashless = false;

    protected AbstractPaymentEntity() {
    }

    protected AbstractPaymentEntity(Integer id, LocalDate date, BigDecimal transaction, boolean cashless) {
        super(id);
        this.date = date;
        setTransaction(transaction);
        this.cashless = cashless;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction.setScale(2, DOWN);
    }

    public boolean isCashless() {
        return cashless;
    }

    public void setCashless(boolean cashless) {
        this.cashless = cashless;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, %s, %s, %s)", getClass().getName(), id, date, transaction, cashless);
    }
}
