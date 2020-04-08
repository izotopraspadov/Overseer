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
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class OrderTestData {

    public static final int INVALID_ORDER_ID = START_SEQUENCE - 1;

    public static final int ORDER_1_ID = START_SEQUENCE + 103;
    public static final int ORDER_2_ID = START_SEQUENCE + 104;
    public static final int ORDER_3_ID = START_SEQUENCE + 105;
    public static final int ORDER_4_ID = START_SEQUENCE + 106;

    public static final Order ORDER_1 =
            new Order(ORDER_1_ID, COMPANY_1, "First Project", false, false, true,
                    LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 10),
                    LocalDate.of(2019, 10, 1), null,
                    BigDecimal.valueOf(100000.00), BigDecimal.valueOf(17000.00), "100", null,
                    GROUP_1, EMPLOYEE_1, true, ORDER_TYPE_1, ORDER_1_PAYMENTS);
    public static final Order ORDER_2 =
            new Order(ORDER_2_ID, COMPANY_1, "First  Estimate", true, true, false,
                    LocalDate.of(2019, 9, 3), LocalDate.of(2019, 9, 3),
                    LocalDate.of(2019, 10, 5), null,
                    BigDecimal.valueOf(10000.00), BigDecimal.valueOf(15000.00), "30-70", 50,
                    GROUP_3, EMPLOYEE_2, true, ORDER_TYPE_2, ORDER_2_PAYMENTS);
    public static final Order ORDER_3 =
            new Order(ORDER_3_ID, COMPANY_2, "Second Project", false, false, true,
                    LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 10),
                    LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 1),
                    BigDecimal.valueOf(200000.00), BigDecimal.valueOf(0.00), "50-50", null,
                    GROUP_5, EMPLOYEE_3, false, ORDER_TYPE_1, ORDER_3_PAYMENTS);
    public static final Order ORDER_4 =
            new Order(ORDER_4_ID, COMPANY_3, "First Legal Service", true, true, false,
                    LocalDate.of(2019, 9, 7), LocalDate.of(2019, 9, 7),
                    LocalDate.of(2019, 10, 7), null,
                    BigDecimal.valueOf(50000.00), BigDecimal.valueOf(10000.00), "20-20-60", null,
                    GROUP_5, EMPLOYEE_4, true, ORDER_TYPE_4, ORDER_4_PAYMENTS);

    public static Order getPreparedCreate() {
        var order = new Order(null, COMPANY_1, "Created Project", false, false, true,
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 10),
                LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 1),
                BigDecimal.valueOf(1000000.00).setScale(2, RoundingMode.DOWN),
                BigDecimal.valueOf(500000.00).setScale(2, RoundingMode.DOWN),
                "50-50", null,
                GROUP_1, EMPLOYEE_1, true, ORDER_TYPE_1, null);

        order.setPayments(OrderPaymentTestData.getPreparedCreateList(order));

        return order;
    }

    public static Order getPreparedUpdate() {
        return new Order(ORDER_1.getId(), ORDER_1.getCompany(), "Updated Project", ORDER_1.isCashless(),
                ORDER_1.isContractIsNeed(), ORDER_1.isContractExists(),
                ORDER_1.getPlannedStartDate(), ORDER_1.getActualStartDate(),
                ORDER_1.getPlannedEndDate(), ORDER_1.getActualEndDate(),
                BigDecimal.valueOf(1000000.00).setScale(2, RoundingMode.DOWN),
                BigDecimal.valueOf(250000.00).setScale(2, RoundingMode.DOWN),
                ORDER_1.getPaymentFormat(), ORDER_1.getNumberOfLines(),
                ORDER_1.getGroup(), ORDER_1.getManager(), ORDER_1.isUnderway(), ORDER_1.getOrderType(), ORDER_1.getPayments());
    }

}
