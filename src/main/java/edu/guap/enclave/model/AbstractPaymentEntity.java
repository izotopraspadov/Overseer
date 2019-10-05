package edu.guap.enclave.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstractPaymentEntity extends AbstractBaseEntity {

    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    @NotNull
    // pattern
    protected LocalDate date;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "transaction")
    @NotNull
    protected BigDecimal transaction;

    public AbstractPaymentEntity() {
    }

    public AbstractPaymentEntity(Integer id, LocalDate date, BigDecimal transaction) {
        super(id);
        this.date = date;
        this.transaction = transaction;
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
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, %s, %s)", getClass().getName(), id, date, transaction);
    }
}
