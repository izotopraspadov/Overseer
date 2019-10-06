package edu.guap.enclave.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = CompanyPayment.DELETE,
                query = "DELETE FROM CompanyPayment cp WHERE cp.id=:id AND cp.company.id=:companyId AND cp.ourCompany.id=:ourCompanyId"),
        @NamedQuery(name = CompanyPayment.ALL_FILTERED,
                query = "SELECT cp FROM CompanyPayment cp WHERE cp.company.id=:companyId AND cp.date=:date"),
        @NamedQuery(name = CompanyPayment.GET,
                query = "SELECT cp FROM CompanyPayment cp WHERE cp.id=:id AND cp.company.id=:companyId")
})

@Entity
@Table(name = "company_payments")
public class CompanyPayment extends AbstractPaymentEntity {

    public static final String DELETE = "CompanyPayment.delete";
    public static final String ALL_FILTERED = "CompanyPayment.getAllFiltered";
    public static final String GET = "CompanyPayment.get";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "our_company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company ourCompany;

    @Column(name = "cashless", nullable = false)
    private boolean cashless;

    @Column(name = "comment")
    private String comment;

    public CompanyPayment() {
    }

    public CompanyPayment(Integer id, LocalDate date, Company company, Company ourCompany,
                          BigDecimal transaction, boolean cashless, String comment) {
        super(id, date, transaction);
        this.company = company;
        this.ourCompany = ourCompany;
        this.cashless = cashless;
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

    public boolean isCashless() {
        return cashless;
    }

    public void setCashless(boolean cashless) {
        this.cashless = cashless;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CompanyPayment{" +
                "id=" + id +
                ", date=" + date +
                ", transaction=" + transaction +
                ", cashless=" + cashless +
                ", comment='" + comment + '\'' +
                '}';
    }
}
