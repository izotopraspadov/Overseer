package edu.born.overseer.model;

import edu.born.overseer.annotation.PaymentFormat;
import edu.born.overseer.model.abstraction.AbstractBaseEntity;
import edu.born.overseer.util.DateTimeUtil;
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
import java.util.*;

import static edu.born.overseer.model.Order.*;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = DELETE,
                query = "DELETE FROM Order o WHERE o.id=:id"),
        @NamedQuery(name = ALL,
                query = "SELECT o FROM Order o WHERE " +
                        "(o.company.id=:companyId OR :companyId IS NULL)" +
                        "AND (o.cashless=:cashless OR :cashless IS NULL) " +
                        "AND (o.group.id=:groupId OR :groupId IS NULL) " +
                        "AND (o.group.id=:groupId OR :groupId IS NULL) " +
                        "AND (o.contractIsNeed=:contractIsNeed OR :contractIsNeed IS NULL) " +
                        "AND (o.contractExists=:contractExists OR :contractExists IS NULL) " +
                        "AND (o.plannedStartDate=:plannedStartDate OR :plannedStartDate IS NULL) " +
                        "AND (o.actualStartDate=:actualStartDate OR :actualStartDate IS NULL) " +
                        "AND (o.plannedEndDate=:plannedEndDate OR :plannedEndDate IS NULL) " +
                        "AND (o.actualEndDate=:actualEndDate OR :actualEndDate IS NULL) " +
                        "AND (o.sum=:currentSum OR :currentSum IS NULL) " +
                        "AND (o.manager.id=:managerId OR :managerId IS NULL) " +
                        "AND (o.underway=:underway OR :underway IS NULL) " +
                        "AND (o.expectedPayment=:expectedPayment OR :expectedPayment IS NULL) " +
                        "AND (o.numberOfLines=:numberOfLines OR :numberOfLines IS NULL) " +
                        "AND (lower(o.paymentFormat) LIKE lower(concat('%', :paymentFormat, '%'))) " +
                        "AND (lower(o.title) LIKE lower(concat('%', :title, '%'))) " +
                        "ORDER BY o.title")
})
public class Order extends AbstractBaseEntity {
    // not inherited from AbstractTitleEntity, due to differences in the uniqueness of the title field

    public static final String ALL = "Order:all";
    public static final String DELETE = "Order:delete";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotBlank
    @Size(max = 255)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "cashless", nullable = false)
    private boolean cashless = false;

    @Column(name = "contract_is_need", nullable = false)
    private boolean contractIsNeed = false;

    @Column(name = "contract_exists", nullable = false)
    private boolean contractExists = false;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "planned_start_date", nullable = false)
    private LocalDate plannedStartDate;

    @NotNull
    @Column(name = "actual_start_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate actualStartDate;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "planned_end_date", nullable = false)
    private LocalDate plannedEndDate;

    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    @NotNull
    @Digits(integer = 11, fraction = 2)
    @Column(name = "sum")
    private BigDecimal sum;

    @NotNull
    @Digits(integer = 11, fraction = 2)
    @Column(name = "expected_payment", nullable = false)
    private BigDecimal expectedPayment = BigDecimal.ZERO;

    @PaymentFormat
    @Column(name = "payment_format", nullable = false)
    private String paymentFormat;

    @Range(max = 200)
    @Column(name = "number_of_lines")
    private Integer numberOfLines = 0;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "manager_id", nullable = false)
    private Employee manager;

    @Column(name = "underway", nullable = false)
    private boolean underway = true;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_type_id", nullable = false)
    private OrderType orderType;

    @OrderBy("date DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<ActualTime> actualTime = new HashSet<>();

    @OrderBy("manHours DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<PlannedTime> plannedTime = new HashSet<>();

    @OrderBy("date DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderPayment> payments = new HashSet<>();

    @OrderBy("dateCompleted DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<Task> tasks = new HashSet<>();

    public Order() {
    }

    /**
     * Cloning constructor
     **/

    public Order(Order other) {
        super(other.getId());
        this.company = other.getCompany();
        this.title = other.title;
        this.cashless = other.isCashless();
        this.contractIsNeed = other.isContractIsNeed();
        this.contractExists = other.isContractExists();
        this.plannedStartDate = other.getPlannedStartDate();
        this.actualStartDate = other.getActualStartDate();
        this.plannedEndDate = other.getPlannedEndDate();
        this.actualEndDate = other.getActualEndDate();
        this.sum = other.getSum();
        this.expectedPayment = other.getExpectedPayment();
        this.paymentFormat = other.getPaymentFormat();
        this.numberOfLines = other.getNumberOfLines();
        this.group = other.getGroup();
        this.manager = other.getManager();
        this.underway = other.isUnderway();
        this.orderType = other.getOrderType();
        this.payments = other.getPayments();
        this.tasks = other.getTasks();
        this.actualTime = other.getActualTime();
        this.plannedTime = other.getPlannedTime();
    }

    public Company getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCashless() {
        return cashless;
    }

    public boolean isContractIsNeed() {
        return contractIsNeed;
    }

    public boolean isContractExists() {
        return contractExists;
    }

    public LocalDate getPlannedStartDate() {
        return plannedStartDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getExpectedPayment() {
        return expectedPayment;
    }

    public String getPaymentFormat() {
        return paymentFormat;
    }

    public Integer getNumberOfLines() {
        return numberOfLines;
    }

    public Group getGroup() {
        return group;
    }

    public Employee getManager() {
        return manager;
    }

    public boolean isUnderway() {
        return underway;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Set<OrderPayment> getPayments() {
        return payments;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<ActualTime> getActualTime() {
        return actualTime;
    }

    public Set<PlannedTime> getPlannedTime() {
        return plannedTime;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCashless(boolean cashless) {
        this.cashless = cashless;
    }

    public void setContractIsNeed(boolean contractIsNeed) {
        this.contractIsNeed = contractIsNeed;
    }

    public void setContractExists(boolean contractExists) {
        this.contractExists = contractExists;
    }

    public void setPlannedStartDate(LocalDate plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void setExpectedPayment(BigDecimal expectedPayment) {
        this.expectedPayment = expectedPayment;
    }

    public void setPaymentFormat(String paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public void setNumberOfLines(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setUnderway(boolean underway) {
        this.underway = underway;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setPayments(Set<OrderPayment> payments) {
        this.payments = payments;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setActualTime(Set<ActualTime> actualTime) {
        this.actualTime = actualTime;
    }

    public void setPlannedTime(Set<PlannedTime> plannedTime) {
        this.plannedTime = plannedTime;
    }

    /**
     * Fluent API
     **/

    public Order id(Integer id) {
        this.id = id;
        return this;
    }

    public Order company(Company company) {
        this.company = company;
        return this;
    }

    public Order title(String title) {
        this.title = title;
        return this;
    }

    public Order cashless(boolean cashless) {
        this.cashless = cashless;
        return this;
    }

    public Order contractIsNeed(boolean contractIsNeed) {
        this.contractIsNeed = contractIsNeed;
        return this;
    }

    public Order contractExists(boolean contractExists) {
        this.contractExists = contractExists;
        return this;
    }

    public Order plannedStartDate(LocalDate plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
        return this;
    }

    public Order actualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
        return this;
    }

    public Order plannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
        return this;
    }

    public Order actualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
        return this;
    }

    public Order sum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public Order expectedPayment(BigDecimal expectedPayment) {
        this.expectedPayment = expectedPayment;
        return this;
    }

    public Order paymentFormat(String paymentFormat) {
        this.paymentFormat = paymentFormat;
        return this;
    }

    public Order numberOfLines(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
        return this;
    }

    public Order group(Group group) {
        this.group = group;
        return this;
    }

    public Order manager(Employee manager) {
        this.manager = manager;
        return this;
    }

    public Order underway(boolean underway) {
        this.underway = underway;
        return this;
    }

    public Order orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public Order payments(Set<OrderPayment> payments) {
        this.payments = payments;
        return this;
    }

    public Order tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Order actualTime(Set<ActualTime> actualTime) {
        this.actualTime = actualTime;
        return this;
    }

    public Order plannedTime(Set<PlannedTime> plannedTime) {
        this.plannedTime = plannedTime;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Order otherOrder = (Order) other;
        return cashless == otherOrder.cashless &&
                contractIsNeed == otherOrder.contractIsNeed &&
                contractExists == otherOrder.contractExists &&
                underway == otherOrder.underway &&
                title.equals(otherOrder.title) &&
                plannedStartDate.equals(otherOrder.plannedStartDate) &&
                actualStartDate.equals(otherOrder.actualStartDate) &&
                plannedEndDate.equals(otherOrder.plannedEndDate) &&
                actualEndDate.equals(otherOrder.actualEndDate) &&
                sum.equals(otherOrder.sum) &&
                expectedPayment.equals(otherOrder.expectedPayment) &&
                paymentFormat.equals(otherOrder.paymentFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, cashless, contractIsNeed, contractExists,
                plannedStartDate, actualStartDate, plannedEndDate, actualEndDate, sum, expectedPayment,
                paymentFormat, underway);
    }

    @Override
    public String toString() {
        return "Order {" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "cashless=" + cashless + ", " +
                "contractIsNeed=" + contractIsNeed + ", " +
                "contractExists=" + contractExists + ", " +
                "plannedStartDate=" + plannedStartDate + ", " +
                "actualStartDate=" + actualStartDate + ", " +
                "plannedEndDate=" + plannedEndDate + ", " +
                "actualEndDate=" + actualEndDate + ", " +
                "sum=" + sum + ", " +
                "expectedPayment=" + expectedPayment + ", " +
                "paymentFormat=" + paymentFormat + ", " +
                "numberOfLines=" + numberOfLines + ", " +
                "underway=" + underway +
                "}\n";
    }

}