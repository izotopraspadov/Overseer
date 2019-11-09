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
import java.util.List;

@Entity
@Table(name = "ordered_objects")
@NamedQueries({
        @NamedQuery(name = OrderedObject.DELETE,
                query = "DELETE FROM OrderedObject o WHERE o.id=:id"),
        @NamedQuery(name = OrderedObject.ALL,
                query = "SELECT o FROM OrderedObject o ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.GET,
                query = "SELECT o FROM OrderedObject o WHERE o.id=:id"),
        @NamedQuery(name = OrderedObject.GET_WITH_PAYMENTS,
                query = "SELECT DISTINCT o FROM OrderedObject o LEFT JOIN FETCH o.payments WHERE o.id=:id"),
        @NamedQuery(name = OrderedObject.GET_WITH_TASKS,
                query = "SELECT DISTINCT o FROM OrderedObject o LEFT JOIN FETCH o.tasks WHERE o.id=:id"),
        @NamedQuery(name = OrderedObject.ALL_BY_TITLE,
                query = "SELECT o FROM OrderedObject o WHERE o.title=:title ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_COMPANY,
                query = "SELECT o FROM OrderedObject o WHERE o.company.id=:companyId ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_CASHLESS,
                query = "SELECT o FROM OrderedObject o WHERE o.cashless=:cashless ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_ORDER_TYPE,
                query = "SELECT o FROM OrderedObject o " +
                        "LEFT JOIN FETCH o.orderType ot " +
                        "WHERE ot.title=:orderType ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_GROUP,
                query = "SELECT o FROM OrderedObject o WHERE o.group.id=:groupId ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_CONTRACT_IS_NEED,
                query = "SELECT o FROM OrderedObject o WHERE o.contractIsNeed=:contractIsNeed ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_CONTRACT_EXISTS,
                query = "SELECT o FROM OrderedObject o WHERE o.contractExists=:contractExists ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_PLANNED_START_DATE,
                query = "SELECT o FROM OrderedObject o WHERE o.plannedStartDate=:date ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_ACTUAL_START_DATE,
                query = "SELECT o FROM OrderedObject o WHERE o.actualStartDate=:date ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_PLANNED_END_DATE,
                query = "SELECT o FROM OrderedObject o WHERE o.plannedEndDate=:date ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_ACTUAL_END_DATE,
                query = "SELECT o FROM OrderedObject o WHERE o.actualEndDate=:date ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_SUM,
                query = "SELECT o FROM OrderedObject o WHERE o.sum=:currentSum ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_MANAGER,
                query = "SELECT o FROM OrderedObject o WHERE o.manager.id=:managerId ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_UNDERWAY,
                query = "SELECT o FROM OrderedObject o WHERE o.underway=:underway ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_EXPECTED_PAYMENT,
                query = "SELECT o FROM OrderedObject o WHERE o.expectedPayment=:expectedPayment ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_PAYMENT_ORDER,
                query = "SELECT o FROM OrderedObject o WHERE o.paymentOrder=:paymentOrder ORDER BY o.title"),
        @NamedQuery(name = OrderedObject.ALL_BY_NUMBER_OF_LINES,
                query = "SELECT o FROM OrderedObject o WHERE o.numberOfLines=:numberOfLines ORDER BY o.title"),
})
public class OrderedObject extends AbstractBaseEntity {

    public static final String DELETE = "OrderedObject.delete";
    public static final String ALL = "OrderedObject.getAll";
    public static final String GET = "OrderedObject.get";
    public static final String GET_WITH_PAYMENTS = "OrderedObject.getWithPayments";
    public static final String GET_WITH_TASKS = "OrderedObject.getWithTasks";
    public static final String ALL_BY_TITLE = "OrderedObject.getAllByTitle";
    public static final String ALL_BY_COMPANY = "OrderedObject.getAllByCompany";
    public static final String ALL_BY_CASHLESS = "OrderedObject.getAllByCashless";
    public static final String ALL_BY_ORDER_TYPE = "OrderedObject.getAllByOrderType";
    public static final String ALL_BY_GROUP = "OrderedObject.getAllByGroup";
    public static final String ALL_BY_CONTRACT_IS_NEED = "OrderedObject.getAllByContractIsNeed";
    public static final String ALL_BY_CONTRACT_EXISTS = "OrderedObject.getAllByContractExists";
    public static final String ALL_BY_PLANNED_START_DATE = "OrderedObject.getAllByPlannedStartDate";
    public static final String ALL_BY_ACTUAL_START_DATE = "OrderedObject.getAllByActualStartDat";
    public static final String ALL_BY_PLANNED_END_DATE = "OrderedObject.getAllByPlannedEndDate";
    public static final String ALL_BY_ACTUAL_END_DATE = "OrderedObject.getAllByActualEndDate";
    public static final String ALL_BY_SUM = "OrderedObject.getAllBySum";
    public static final String ALL_BY_MANAGER = "OrderedObject.getAllByManager";
    public static final String ALL_BY_UNDERWAY = "OrderedObject.getAllByUnderway";
    public static final String ALL_BY_EXPECTED_PAYMENT = "OrderedObject.getAllByExpectedPayment";
    public static final String ALL_BY_PAYMENT_ORDER = "OrderedObject.getAllByPaymentOrder";
    public static final String ALL_BY_NUMBER_OF_LINES = "OrderedObject.getAllByNumberOfLines";

    @ManyToOne(fetch = FetchType.EAGER)
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

    @Digits(integer = 11, fraction = 2)
    @Column(name = "sum")
    @NotNull
    private BigDecimal sum;

    @Digits(integer = 11, fraction = 2)
    @Column(name = "expected_payment", nullable = false)
    @NotNull
    private BigDecimal expectedPayment;

    @Column(name = "payment_order", nullable = false)
    @NotBlank
    // regex
    private String paymentOrder;

    @Column(name = "number_of_lines", nullable = false)
    @Range(max = 200)
    @NotNull
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderedObject")
    @OrderBy("date DESC")
    private List<OrderedObjectPayment> payments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderedObject")
    private List<Task> tasks;

    public OrderedObject() {
    }

    public OrderedObject(Integer id, Company company, String title, boolean cashless, boolean contractIsNeed,
                         boolean contractExists, LocalDate plannedStartDate, LocalDate actualStartDate, LocalDate plannedEndDate,
                         LocalDate actualEndDate, BigDecimal sum, BigDecimal expectedPayment, String paymentOrder, Integer numberOfLines,
                         Group group, Employee manager, boolean underway, OrderType orderType, List<OrderedObjectPayment> payments) {
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
        this.paymentOrder = paymentOrder;
        this.numberOfLines = numberOfLines;
        this.group = group;
        this.manager = manager;
        this.underway = underway;
        this.orderType = orderType;
        this.payments = payments;
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

    public List<OrderedObjectPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderedObjectPayment> payments) {
        this.payments = payments;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
                ", expectedPayment=" + expectedPayment +
                ", orderType=" + orderType +
                ", paymentOrder='" + paymentOrder + '\'' +
                ", numberOfLines=" + numberOfLines +
                ", underway=" + underway +
                '}';
    }
}