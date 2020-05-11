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
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order:delete",
                query = "DELETE FROM Order o WHERE o.id=:id"),
        @NamedQuery(name = "Order:byId",
                query = "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.tasks WHERE o.id=:id"),
        @NamedQuery(name = "Order:byIdWithPayments",
                query = "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.payments WHERE o.id=:id"),
        @NamedQuery(name = "Order:all",
                query = "SELECT o FROM Order o ORDER BY o.title"),
        @NamedQuery(name = "Order:allByCompany",
                query = "SELECT o FROM Order o WHERE o.company.id=:companyId ORDER BY o.title"),
        @NamedQuery(name = "Order:allByCashless",
                query = "SELECT o FROM Order o WHERE o.cashless=:cashless ORDER BY o.title"),
        @NamedQuery(name = "Order:allByGroup",
                query = "SELECT o FROM Order o WHERE o.group.id=:groupId ORDER BY o.title"),
        @NamedQuery(name = "Order:allByContractIsNeed",
                query = "SELECT o FROM Order o WHERE o.contractIsNeed=:contractIsNeed ORDER BY o.title"),
        @NamedQuery(name = "Order:allByContractExists",
                query = "SELECT o FROM Order o WHERE o.contractExists=:contractExists ORDER BY o.title"),
        @NamedQuery(name = "Order:allByPlannedStartDate",
                query = "SELECT o FROM Order o WHERE o.plannedStartDate=:date ORDER BY o.title"),
        @NamedQuery(name = "Order:allByActualStartDate",
                query = "SELECT o FROM Order o WHERE o.actualStartDate=:date ORDER BY o.title"),
        @NamedQuery(name = "Order:allByPlannedEndDate",
                query = "SELECT o FROM Order o WHERE o.plannedEndDate=:date ORDER BY o.title"),
        @NamedQuery(name = "Order:allByActualEndDate",
                query = "SELECT o FROM Order o WHERE o.actualEndDate=:date ORDER BY o.title"),
        @NamedQuery(name = "Order:allBySum",
                query = "SELECT o FROM Order o WHERE o.sum=:currentSum ORDER BY o.title"),
        @NamedQuery(name = "Order:allByManager",
                query = "SELECT o FROM Order o WHERE o.manager.id=:managerId ORDER BY o.title"),
        @NamedQuery(name = "Order:allByUnderway",
                query = "SELECT o FROM Order o WHERE o.underway=:underway ORDER BY o.title"),
        @NamedQuery(name = "Order:allByExpectedPayment",
                query = "SELECT o FROM Order o WHERE o.expectedPayment=:expectedPayment ORDER BY o.title"),
        @NamedQuery(name = "Order:allByNumberOfLines",
                query = "SELECT o FROM Order o WHERE o.numberOfLines=:numberOfLines ORDER BY o.title"),
        @NamedQuery(name = "Order:allByPaymentFormat",
                query = "SELECT o FROM Order o WHERE lower(o.paymentFormat) LIKE lower(concat(:paymentFormat, '%')) ORDER BY o.title"),
        @NamedQuery(name = "Order:allByTitle",
                query = "SELECT o FROM Order o WHERE lower(o.title) LIKE lower(concat(:title, '%')) ORDER BY o.title"),
        @NamedQuery(name = "Order:allByOrderType",
                query = "SELECT o FROM Order o LEFT JOIN FETCH o.orderType ot WHERE lower(ot.title) LIKE lower(concat(:orderType, '%')) ORDER BY o.title"),
})
public class Order extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Company company;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String title;

    @Column(name = "cashless", nullable = false)
    private boolean cashless;

    @Column(name = "contract_is_need", nullable = false)
    private boolean contractIsNeed;

    @Column(name = "contract_exists", nullable = false)
    private boolean contractExists;

    @Column(name = "planned_start_date", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @NotNull
    private LocalDate plannedStartDate;

    @Column(name = "actual_start_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @NotNull
    private LocalDate actualStartDate;

    @Column(name = "planned_end_date", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    @NotNull
    private LocalDate plannedEndDate;

    @Column(name = "actual_end_date")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate actualEndDate;

    @Column(name = "sum")
    @Digits(integer = 11, fraction = 2)
    @NotNull
    private BigDecimal sum;

    @Column(name = "expected_payment", nullable = false)
    @Digits(integer = 11, fraction = 2)
    @NotNull
    private BigDecimal expectedPayment;

    @Column(name = "payment_format", nullable = false)
    @PaymentFormat
    private String paymentFormat;

    @Column(name = "number_of_lines", nullable = false)
    @Range(max = 200)
    private Integer numberOfLines;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @OrderBy("date DESC")
    private List<OrderPayment> payments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {PERSIST, MERGE, REMOVE})
    private List<Task> tasks;

    public Order() {
    }

    public Order(Integer id, Company company, String title, boolean cashless, boolean contractIsNeed,
                 boolean contractExists, LocalDate plannedStartDate, LocalDate actualStartDate, LocalDate plannedEndDate,
                 LocalDate actualEndDate, BigDecimal sum, BigDecimal expectedPayment, String paymentFormat, Integer numberOfLines,
                 Group group, Employee manager, boolean underway, OrderType orderType, List<OrderPayment> payments) {

        this(id, company, title, cashless, contractIsNeed, contractExists, plannedStartDate,
                actualStartDate, plannedEndDate, actualEndDate, sum, expectedPayment, paymentFormat,
                numberOfLines, group, manager, underway, orderType, payments, null);

    }

    public Order(Integer id, Company company, String title, boolean cashless, boolean contractIsNeed,
                 boolean contractExists, LocalDate plannedStartDate, LocalDate actualStartDate, LocalDate plannedEndDate,
                 LocalDate actualEndDate, BigDecimal sum, BigDecimal expectedPayment, String paymentFormat, Integer numberOfLines,
                 Group group, Employee manager, boolean underway, OrderType orderType, List<OrderPayment> payments, List<Task> tasks) {
        super(id);
        this.company = company;
        this.title = title;
        this.cashless = cashless;
        this.contractIsNeed = contractIsNeed;
        this.contractExists = contractExists;
        this.plannedStartDate = plannedStartDate;
        this.actualStartDate = actualStartDate;
        this.plannedEndDate = plannedEndDate;
        this.actualEndDate = actualEndDate;
        this.sum = sum;
        this.expectedPayment = expectedPayment;
        this.paymentFormat = paymentFormat;
        this.numberOfLines = numberOfLines;
        this.group = group;
        this.manager = manager;
        this.underway = underway;
        this.orderType = orderType;
        this.payments = payments;
        this.tasks = tasks;
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

    public BigDecimal getExpectedPayment() {
        return expectedPayment;
    }

    public void setExpectedPayment(BigDecimal expectedPayment) {
        this.expectedPayment = expectedPayment;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType objectType) {
        this.orderType = objectType;
    }

    public String getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(String paymentOrder) {
        this.paymentFormat = paymentOrder;
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

    public List<OrderPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPayment> payments) {
        this.payments = payments;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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