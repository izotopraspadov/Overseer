package edu.born.overseer;

import edu.born.overseer.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.GroupTestData.*;
import static edu.born.overseer.OrderPaymentTestData.*;
import static edu.born.overseer.OrderTypeTestData.*;
import static edu.born.overseer.TaskTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class OrderTestData {

    public static final int ORDER_1_ID = START_SEQUENCE + 103;
    public static final int ORDER_2_ID = START_SEQUENCE + 104;
    public static final int ORDER_3_ID = START_SEQUENCE + 105;
    public static final int ORDER_4_ID = START_SEQUENCE + 106;

    public static final String INVALID_PAYMENT_FORMAT = "100-100";

    public static final Order ORDER_1 = new Order()
            .id(ORDER_1_ID)
            .company(COMPANY_1)
            .title("First Project")
            .contractExists(true)
            .plannedStartDate(LocalDate.of(2019, 9, 1))
            .actualStartDate(LocalDate.of(2019, 9, 10))
            .plannedEndDate(LocalDate.of(2019, 10, 1))
            .sum(BigDecimal.valueOf(100000.00))
            .expectedPayment(BigDecimal.valueOf(17000.00))
            .paymentFormat("100")
            .group(GROUP_1)
            .manager(EMPLOYEE_1)
            .underway(true)
            .orderType(ORDER_TYPE_1)
            .payments(ORDER_1_PAYMENTS)
            .tasks(ORDER_1_TASKS);

    public static final Order ORDER_2 = new Order()
            .id(ORDER_2_ID)
            .company(COMPANY_1)
            .title("First  Estimate")
            .cashless(true)
            .contractIsNeed(true)
            .plannedStartDate(LocalDate.of(2019, 9, 3))
            .actualStartDate(LocalDate.of(2019, 9, 3))
            .plannedEndDate(LocalDate.of(2019, 10, 5))
            .sum(BigDecimal.valueOf(10000.00))
            .expectedPayment(BigDecimal.valueOf(15000.00))
            .paymentFormat("30-70")
            .numberOfLines(50)
            .group(GROUP_3)
            .manager(EMPLOYEE_2)
            .underway(true)
            .orderType(ORDER_TYPE_2)
            .payments(ORDER_2_PAYMENTS)
            .tasks(ORDER_2_TASKS);

    public static final Order ORDER_3 = new Order()
            .id(ORDER_3_ID)
            .company(COMPANY_2)
            .title("Second Project")
            .contractExists(true)
            .plannedStartDate(LocalDate.of(2019, 9, 1))
            .actualStartDate(LocalDate.of(2019, 9, 10))
            .plannedEndDate(LocalDate.of(2019, 10, 1))
            .actualEndDate(LocalDate.of(2019, 10, 1))
            .sum(BigDecimal.valueOf(200000.00))
            .paymentFormat("50-50")
            .group(GROUP_5)
            .manager(EMPLOYEE_3)
            .orderType(ORDER_TYPE_1)
            .payments(ORDER_3_PAYMENTS)
            .tasks(ORDER_3_TASKS);

    public static final Order ORDER_4 = new Order()
            .id(ORDER_4_ID)
            .company(COMPANY_3)
            .title("First Legal Service")
            .cashless(true)
            .contractIsNeed(true)
            .plannedStartDate(LocalDate.of(2019, 9, 7))
            .actualStartDate(LocalDate.of(2019, 9, 7))
            .plannedEndDate(LocalDate.of(2019, 10, 7))
            .sum(BigDecimal.valueOf(50000.00))
            .expectedPayment(BigDecimal.valueOf(10000.00))
            .paymentFormat("20-20-60")
            .group(GROUP_5)
            .manager(EMPLOYEE_4)
            .underway(true)
            .orderType(ORDER_TYPE_4)
            .payments(ORDER_4_PAYMENTS)
            .tasks(ORDER_4_TASKS);


    public static Order getPreparedCreate() {
        var order = new Order()
                .company(COMPANY_1)
                .title("Created Project")
                .contractExists(true)
                .plannedStartDate(LocalDate.of(2019, 9, 1))
                .actualStartDate(LocalDate.of(2019, 9, 10))
                .plannedEndDate(LocalDate.of(2019, 10, 1))
                .actualEndDate(LocalDate.of(2019, 10, 1))
                .sum(BigDecimal.valueOf(1000000.00)
                        .setScale(2, RoundingMode.DOWN))
                .expectedPayment(BigDecimal.valueOf(500000.00)
                        .setScale(2, RoundingMode.DOWN))
                .paymentFormat("50-50")
                .group(GROUP_1)
                .manager(EMPLOYEE_1)
                .underway(true)
                .orderType(ORDER_TYPE_1);

        order.getPayments().addAll(OrderPaymentTestData.getPreparedCreateList(order));
        order.getTasks().addAll(TaskTestData.getPreparedCreateSet(order));

        return order;
    }

    public static Order getPreparedCreateWithInvalidPaymentFormat() {

        return getPreparedCreate()
                .paymentFormat(INVALID_PAYMENT_FORMAT);
    }

    public static Order getPreparedUpdate() {

        return new Order(ORDER_1)
                .title("Updated Project")
                .sum(BigDecimal.valueOf(1000000.00)
                        .setScale(2, RoundingMode.DOWN))
                .expectedPayment(BigDecimal.valueOf(250000.00)
                        .setScale(2, RoundingMode.DOWN));
    }

}
