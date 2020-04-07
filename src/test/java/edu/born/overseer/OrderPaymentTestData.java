package edu.born.overseer;

import edu.born.overseer.model.OrderPayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.OrderTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class OrderPaymentTestData {

    public static final int INVALID_ORDER_PAYMENT_ID = START_SEQUENCE - 1;

    public static final int ORDER_PAYMENT_1_ID = START_SEQUENCE + 107;
    public static final int ORDER_PAYMENT_2_ID = START_SEQUENCE + 108;
    public static final int ORDER_PAYMENT_3_ID = START_SEQUENCE + 109;
    public static final int ORDER_PAYMENT_4_ID = START_SEQUENCE + 110;
    public static final int ORDER_PAYMENT_5_ID = START_SEQUENCE + 111;
    public static final int ORDER_PAYMENT_6_ID = START_SEQUENCE + 112;
    public static final int ORDER_PAYMENT_7_ID = START_SEQUENCE + 113;
    public static final int ORDER_PAYMENT_8_ID = START_SEQUENCE + 114;

    public static final OrderPayment ORDER_PAYMENT_1 =
            new OrderPayment(ORDER_PAYMENT_1_ID, LocalDate.of(2019, 9, 11),
                    COMPANY_1, ORDER_1, COMPANY_1, BigDecimal.valueOf(100000.00), true, "$$$");
    public static final OrderPayment ORDER_PAYMENT_2 =
            new OrderPayment(ORDER_PAYMENT_2_ID, LocalDate.of(2019, 9, 13),
                    COMPANY_1, ORDER_2, COMPANY_1, BigDecimal.valueOf(3000.00), false, null);
    public static final OrderPayment ORDER_PAYMENT_3 =
            new OrderPayment(ORDER_PAYMENT_3_ID, LocalDate.of(2019, 9, 15),
                    COMPANY_1, ORDER_2, COMPANY_1, BigDecimal.valueOf(7000.00), false, "%%%");
    public static final OrderPayment ORDER_PAYMENT_4 =
            new OrderPayment(ORDER_PAYMENT_4_ID, LocalDate.of(2019, 9, 15),
                    COMPANY_2, ORDER_3, COMPANY_1, BigDecimal.valueOf(100000.00), true, null);
    public static final OrderPayment ORDER_PAYMENT_5 =
            new OrderPayment(ORDER_PAYMENT_5_ID, LocalDate.of(2019, 9, 19),
                    COMPANY_2, ORDER_3, COMPANY_1, BigDecimal.valueOf(100000.00), true, null);
    public static final OrderPayment ORDER_PAYMENT_6 =
            new OrderPayment(ORDER_PAYMENT_6_ID, LocalDate.of(2019, 9, 20),
                    COMPANY_3, ORDER_4, COMPANY_1, BigDecimal.valueOf(10000.00), false, null);
    public static final OrderPayment ORDER_PAYMENT_7 =
            new OrderPayment(ORDER_PAYMENT_7_ID, LocalDate.of(2019, 9, 29),
                    COMPANY_3, ORDER_4, COMPANY_1, BigDecimal.valueOf(10000.00), true, "777");
    public static final OrderPayment ORDER_PAYMENT_8 =
            new OrderPayment(ORDER_PAYMENT_8_ID, LocalDate.of(2019, 9, 29),
                    COMPANY_3, ORDER_4, COMPANY_1, BigDecimal.valueOf(30000.00), false, "Done!");

    public static final List<OrderPayment> ORDER_1_PAYMENTS = List.of(ORDER_PAYMENT_1);
    public static final List<OrderPayment> ORDER_2_PAYMENTS = List.of(ORDER_PAYMENT_2, ORDER_PAYMENT_3);
    public static final List<OrderPayment> ORDER_3_PAYMENTS = List.of(ORDER_PAYMENT_4, ORDER_PAYMENT_5);
    public static final List<OrderPayment> ORDER_4_PAYMENTS = List.of(ORDER_PAYMENT_6, ORDER_PAYMENT_7, ORDER_PAYMENT_8);



}
