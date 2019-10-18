package edu.guap.enclave.model;

import edu.guap.enclave.model.abstract_entities.AbstractBaseEntity;
import edu.guap.enclave.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordered_objects")
public class OrderedObject extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @Column(name = "cashless", nullable = false)
    private boolean cashless;

    @Column(name = "contract_is_need", nullable = false)
    private boolean contractIsNeed;

    @Column(name = "contract_exists", nullable = false)
    private boolean contractExists;

    @Column(name = "planned_start_date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate plannedStartDate;

    @Column(name = "actual_start_date")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate actualStartDate;

    @Column(name = "planned_end_date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate plannedEndDate;

    @Column(name = "actual_end_date")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate actualEndDate;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "sum")
    @NotNull
    private BigDecimal sum;

    @Column(name = "payment_order", nullable = false)
    @NotBlank
    // regex
    private String paymentOrder;

    @Column(name = "number_of_lines", nullable = false)
    @Range(max = 200)
    @NotNull
    private Integer numberOfLines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Employee manager;

    @Column(name = "underway", nullable = false)
    private boolean underway;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private OrderType orderType;

    public OrderedObject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isCashless() {
        return cashless;
    }

    public void setCashless(boolean cashless) {
        this.cashless = cashless;
    }

    public boolean isContractIsNeed() {
        return contractIsNeed;
    }

    public void setContractIsNeed(boolean contractIsNeed) {
        this.contractIsNeed = contractIsNeed;
    }

    public boolean isContractExists() {
        return contractExists;
    }

    public void setContractExists(boolean contractExists) {
        this.contractExists = contractExists;
    }

    public LocalDate getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(LocalDate plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType objectType) {
        this.orderType = objectType;
    }

    public String getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(String paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Integer getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean isUnderway() {
        return underway;
    }

    public void setUnderway(boolean underway) {
        this.underway = underway;
    }

    @Override
    public String toString() {
        return "OrderedObject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cashless=" + cashless +
                ", contractIsNeed=" + contractIsNeed +
                ", contractExists=" + contractExists +
                ", plannedStartDate=" + plannedStartDate +
                ", actualStartDate=" + actualStartDate +
                ", plannedEndDate=" + plannedEndDate +
                ", actualEndDate=" + actualEndDate +
                ", sum=" + sum +
                ", orderType=" + orderType +
                ", paymentOrder='" + paymentOrder + '\'' +
                ", numberOfLines=" + numberOfLines +
                ", underway=" + underway +
                '}';
    }
}
